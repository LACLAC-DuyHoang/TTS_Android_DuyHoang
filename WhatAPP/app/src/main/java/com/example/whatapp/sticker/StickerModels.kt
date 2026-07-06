package com.example.whatapp.sticker

import android.content.Context
import com.example.whatapp.BuildConfig
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

data class Sticker(
    val imageFileName: String,
    val emojis: List<String>,
    val accessibilityText: String
)

data class StickerPack(
    val identifier: String,
    val name: String,
    val publisher: String,
    val trayImageFile: String,
    val stickers: List<Sticker>,
    val publisherEmail: String = "",
    val publisherWebsite: String = "",
    val privacyPolicyWebsite: String = "",
    val licenseAgreementWebsite: String = "",
    val imageDataVersion: String = "1",
    val androidPlayStoreLink: String = "",
    val iosAppStoreLink: String = "",
    val animatedStickerPack: Boolean = false
)

object StickerRepository {
    const val TRAY_IMAGE_FILE = "tray_icon.png"
    private const val PACK_MANIFEST = "pack.json"

    fun contentProviderAuthority(): String = "${BuildConfig.APPLICATION_ID}.stickercontentprovider"

    fun stickersRoot(context: Context): File = File(context.filesDir, "stickers")

    fun packDirectory(context: Context, identifier: String): File =
        File(stickersRoot(context), identifier)

    fun createPack(
        context: Context,
        identifier: String,
        name: String,
        stickers: List<Sticker>
    ): StickerPack {
        val packageName = context.packageName
        return StickerPack(
            identifier = identifier,
            name = name.ifBlank { "My Sticker Pack" },
            publisher = "TTS Demo",
            trayImageFile = TRAY_IMAGE_FILE,
            stickers = stickers,
            androidPlayStoreLink = "https://play.google.com/store/apps/details?id=$packageName"
        )
    }

    fun savePack(context: Context, pack: StickerPack) {
        val directory = packDirectory(context, pack.identifier)
        if (!directory.exists() && !directory.mkdirs()) {
            error("Cannot create pack directory: ${directory.absolutePath}")
        }
        File(directory, PACK_MANIFEST).writeText(pack.toJson().toString(), Charsets.UTF_8)
    }

    fun loadPreparedPacks(context: Context): List<StickerPack> {
        val root = stickersRoot(context)
        if (!root.isDirectory) return emptyList()
        return root.listFiles()
            ?.filter { it.isDirectory }
            ?.mapNotNull { directory ->
                runCatching {
                    val pack = JSONObject(File(directory, PACK_MANIFEST).readText(Charsets.UTF_8)).toStickerPack()
                    if (isPrepared(context, pack)) pack else null
                }.getOrNull()
            }
            .orEmpty()
    }

    fun findPack(context: Context, identifier: String?): StickerPack? =
        loadPreparedPacks(context).firstOrNull { it.identifier == identifier }

    fun latestPreparedPack(context: Context): StickerPack? =
        loadPreparedPacks(context).maxByOrNull { packDirectory(context, it.identifier).lastModified() }

    fun isPrepared(context: Context, pack: StickerPack): Boolean {
        val dir = packDirectory(context, pack.identifier)
        return File(dir, pack.trayImageFile).isFile && pack.stickers.all { File(dir, it.imageFileName).isFile }
    }

    private fun StickerPack.toJson(): JSONObject =
        JSONObject()
            .put("identifier", identifier)
            .put("name", name)
            .put("publisher", publisher)
            .put("tray_image_file", trayImageFile)
            .put("image_data_version", imageDataVersion)
            .put("android_play_store_link", androidPlayStoreLink)
            .put("ios_app_store_link", iosAppStoreLink)
            .put("publisher_email", publisherEmail)
            .put("publisher_website", publisherWebsite)
            .put("privacy_policy_website", privacyPolicyWebsite)
            .put("license_agreement_website", licenseAgreementWebsite)
            .put("animated_sticker_pack", animatedStickerPack)
            .put(
                "stickers",
                JSONArray().apply {
                    stickers.forEach { sticker ->
                        put(
                            JSONObject()
                                .put("image_file", sticker.imageFileName)
                                .put("emojis", JSONArray(sticker.emojis))
                                .put("accessibility_text", sticker.accessibilityText)
                        )
                    }
                }
            )

    private fun JSONObject.toStickerPack(): StickerPack {
        val stickersJson = getJSONArray("stickers")
        val stickers = buildList {
            for (index in 0 until stickersJson.length()) {
                val stickerJson = stickersJson.getJSONObject(index)
                val emojisJson = stickerJson.optJSONArray("emojis") ?: JSONArray()
                add(
                    Sticker(
                        imageFileName = stickerJson.getString("image_file"),
                        emojis = buildList {
                            for (emojiIndex in 0 until emojisJson.length()) {
                                add(emojisJson.getString(emojiIndex))
                            }
                        },
                        accessibilityText = stickerJson.optString("accessibility_text")
                    )
                )
            }
        }
        return StickerPack(
            identifier = getString("identifier"),
            name = getString("name"),
            publisher = getString("publisher"),
            trayImageFile = getString("tray_image_file"),
            stickers = stickers,
            publisherEmail = optString("publisher_email"),
            publisherWebsite = optString("publisher_website"),
            privacyPolicyWebsite = optString("privacy_policy_website"),
            licenseAgreementWebsite = optString("license_agreement_website"),
            imageDataVersion = optString("image_data_version", "1"),
            androidPlayStoreLink = optString("android_play_store_link"),
            iosAppStoreLink = optString("ios_app_store_link"),
            animatedStickerPack = optBoolean("animated_sticker_pack", false)
        )
    }
}
