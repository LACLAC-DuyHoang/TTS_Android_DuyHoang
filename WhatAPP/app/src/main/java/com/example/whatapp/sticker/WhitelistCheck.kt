package com.example.whatapp.sticker

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri

object WhitelistCheck {
    const val CONSUMER_WHATSAPP_PACKAGE_NAME = "com.whatsapp"
    const val BUSINESS_WHATSAPP_PACKAGE_NAME = "com.whatsapp.w4b"

    fun isAnyWhatsAppInstalled(context: Context): Boolean =
        isPackageInstalled(context, CONSUMER_WHATSAPP_PACKAGE_NAME) ||
            isPackageInstalled(context, BUSINESS_WHATSAPP_PACKAGE_NAME)

    fun isWhitelisted(context: Context, identifier: String): Boolean {
        val consumer = isWhitelistedFromProvider(context, identifier, CONSUMER_WHATSAPP_PACKAGE_NAME)
        val business = isWhitelistedFromProvider(context, identifier, BUSINESS_WHATSAPP_PACKAGE_NAME)
        return consumer || business
    }

    private fun isWhitelistedFromProvider(
        context: Context,
        identifier: String,
        whatsappPackageName: String
    ): Boolean {
        if (!isPackageInstalled(context, whatsappPackageName)) return false
        val authority = "$whatsappPackageName.provider.sticker_whitelist_check"
        val providerInfo = context.packageManager.resolveContentProvider(authority, PackageManager.GET_META_DATA)
            ?: return false
        val queryUri = Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(providerInfo.authority)
            .appendPath("is_whitelisted")
            .appendQueryParameter("authority", StickerRepository.contentProviderAuthority())
            .appendQueryParameter("identifier", identifier)
            .build()
        return try {
            context.contentResolver.query(queryUri, null, null, null, null)?.use { cursor ->
                cursor.moveToFirst() && cursor.getInt(cursor.getColumnIndexOrThrow("result")) == 1
            } == true
        } catch (_: Exception) {
            false
        }
    }

    private fun isPackageInstalled(context: Context, packageName: String): Boolean =
        try {
            context.packageManager.getApplicationInfo(packageName, 0).enabled
        } catch (_: PackageManager.NameNotFoundException) {
            false
        }
}
