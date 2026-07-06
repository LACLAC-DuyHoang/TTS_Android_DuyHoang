package com.example.whatapp.sticker

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.text.TextUtils
import java.io.File

class StickerContentProvider : ContentProvider() {
    private val matcher = UriMatcher(UriMatcher.NO_MATCH)

    override fun onCreate(): Boolean {
        val context = requireNotNull(context)
        val authority = StickerRepository.contentProviderAuthority()
        check(authority.startsWith(context.packageName)) {
            "Sticker content provider authority must start with package name."
        }

        matcher.addURI(authority, METADATA, METADATA_CODE)
        matcher.addURI(authority, "$METADATA/*", METADATA_SINGLE_PACK_CODE)
        matcher.addURI(authority, "$STICKERS/*", STICKERS_CODE)
        matcher.addURI(authority, "$STICKERS_ASSET/*/*", STICKERS_ASSET_CODE)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor = when (matcher.match(uri)) {
        METADATA_CODE -> getStickerPackInfo(uri, StickerRepository.loadPreparedPacks(requireNotNull(context)))
        METADATA_SINGLE_PACK_CODE -> {
            val identifier = uri.lastPathSegment
            val packs = StickerRepository.findPack(requireNotNull(context), identifier)?.let(::listOf).orEmpty()
            getStickerPackInfo(uri, packs)
        }
        STICKERS_CODE -> getStickersForPack(uri)
        else -> throw IllegalArgumentException("Unknown URI: $uri")
    }

    override fun openAssetFile(uri: Uri, mode: String): AssetFileDescriptor? {
        if (matcher.match(uri) != STICKERS_ASSET_CODE) return null
        val pathSegments = uri.pathSegments
        if (pathSegments.size != 3) return null
        val identifier = pathSegments[1]
        val fileName = pathSegments[2]
        val pack = StickerRepository.findPack(requireNotNull(context), identifier) ?: return null
        val allowedFiles = pack.stickers.map { it.imageFileName }.toSet() + pack.trayImageFile
        if (fileName !in allowedFiles) return null

        val file = File(StickerRepository.packDirectory(requireNotNull(context), pack.identifier), fileName)
        if (!file.isFile) return null
        val descriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        return AssetFileDescriptor(descriptor, 0, AssetFileDescriptor.UNKNOWN_LENGTH)
    }

    override fun getType(uri: Uri): String = when (matcher.match(uri)) {
        METADATA_CODE -> "vnd.android.cursor.dir/vnd.${StickerRepository.contentProviderAuthority()}.$METADATA"
        METADATA_SINGLE_PACK_CODE -> "vnd.android.cursor.item/vnd.${StickerRepository.contentProviderAuthority()}.$METADATA"
        STICKERS_CODE -> "vnd.android.cursor.dir/vnd.${StickerRepository.contentProviderAuthority()}.$STICKERS"
        STICKERS_ASSET_CODE -> if (uri.lastPathSegment == StickerRepository.TRAY_IMAGE_FILE) "image/png" else "image/webp"
        else -> throw IllegalArgumentException("Unknown URI: $uri")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        throw UnsupportedOperationException("Insert is not supported")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Delete is not supported")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Update is not supported")
    }

    private fun getStickerPackInfo(uri: Uri, stickerPacks: List<StickerPack>): Cursor {
        val cursor = MatrixCursor(
            arrayOf(
                STICKER_PACK_IDENTIFIER_IN_QUERY,
                STICKER_PACK_NAME_IN_QUERY,
                STICKER_PACK_PUBLISHER_IN_QUERY,
                STICKER_PACK_ICON_IN_QUERY,
                ANDROID_APP_DOWNLOAD_LINK_IN_QUERY,
                IOS_APP_DOWNLOAD_LINK_IN_QUERY,
                PUBLISHER_EMAIL,
                PUBLISHER_WEBSITE,
                PRIVACY_POLICY_WEBSITE,
                LICENSE_AGREEMENT_WEBSITE,
                IMAGE_DATA_VERSION,
                AVOID_CACHE,
                ANIMATED_STICKER_PACK
            )
        )
        stickerPacks.forEach { pack ->
            cursor.addRow(
                arrayOf<Any?>(
                    pack.identifier,
                    pack.name,
                    pack.publisher,
                    pack.trayImageFile,
                    pack.androidPlayStoreLink,
                    pack.iosAppStoreLink,
                    pack.publisherEmail,
                    pack.publisherWebsite,
                    pack.privacyPolicyWebsite,
                    pack.licenseAgreementWebsite,
                    pack.imageDataVersion,
                    0,
                    if (pack.animatedStickerPack) 1 else 0
                )
            )
        }
        cursor.setNotificationUri(requireNotNull(context).contentResolver, uri)
        return cursor
    }

    private fun getStickersForPack(uri: Uri): Cursor {
        val identifier = uri.lastPathSegment
        val cursor = MatrixCursor(
            arrayOf(
                STICKER_FILE_NAME_IN_QUERY,
                STICKER_FILE_EMOJI_IN_QUERY,
                STICKER_FILE_ACCESSIBILITY_TEXT_IN_QUERY
            )
        )
        val pack = StickerRepository.findPack(requireNotNull(context), identifier)
        if (pack != null) {
            pack.stickers.forEach { sticker ->
                cursor.addRow(
                    arrayOf(
                        sticker.imageFileName,
                        TextUtils.join(",", sticker.emojis),
                        sticker.accessibilityText
                    )
                )
            }
        }
        cursor.setNotificationUri(requireNotNull(context).contentResolver, uri)
        return cursor
    }

    companion object {
        const val STICKER_PACK_IDENTIFIER_IN_QUERY = "sticker_pack_identifier"
        const val STICKER_PACK_NAME_IN_QUERY = "sticker_pack_name"
        const val STICKER_PACK_PUBLISHER_IN_QUERY = "sticker_pack_publisher"
        const val STICKER_PACK_ICON_IN_QUERY = "sticker_pack_icon"
        const val ANDROID_APP_DOWNLOAD_LINK_IN_QUERY = "android_play_store_link"
        const val IOS_APP_DOWNLOAD_LINK_IN_QUERY = "ios_app_download_link"
        const val PUBLISHER_EMAIL = "sticker_pack_publisher_email"
        const val PUBLISHER_WEBSITE = "sticker_pack_publisher_website"
        const val PRIVACY_POLICY_WEBSITE = "sticker_pack_privacy_policy_website"
        const val LICENSE_AGREEMENT_WEBSITE = "sticker_pack_license_agreement_website"
        const val IMAGE_DATA_VERSION = "image_data_version"
        const val AVOID_CACHE = "whatsapp_will_not_cache_stickers"
        const val ANIMATED_STICKER_PACK = "animated_sticker_pack"
        const val STICKER_FILE_NAME_IN_QUERY = "sticker_file_name"
        const val STICKER_FILE_EMOJI_IN_QUERY = "sticker_emoji"
        const val STICKER_FILE_ACCESSIBILITY_TEXT_IN_QUERY = "sticker_accessibility_text"

        private const val METADATA = "metadata"
        private const val STICKERS = "stickers"
        private const val STICKERS_ASSET = "stickers_asset"
        private const val METADATA_CODE = 1
        private const val METADATA_SINGLE_PACK_CODE = 2
        private const val STICKERS_CODE = 3
        private const val STICKERS_ASSET_CODE = 4

        fun metadataUri(): Uri =
            Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(StickerRepository.contentProviderAuthority())
                .appendPath(METADATA)
                .build()
    }
}
