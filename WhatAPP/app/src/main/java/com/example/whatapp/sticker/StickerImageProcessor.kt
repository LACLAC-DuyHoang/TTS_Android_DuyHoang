package com.example.whatapp.sticker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.net.Uri
import android.os.Build
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class StickerImageProcessor(
    private val context: Context
) {
    suspend fun preparePackFromPhoneImages(
        imageUris: List<Uri>,
        packName: String
    ): StickerPack = withContext(Dispatchers.IO) {
        require(imageUris.size >= MIN_STICKERS_PER_PACK) {
            "WhatsApp needs at least 3 stickers in one pack."
        }
        val appContext = context.applicationContext
        val identifier = "custom_${System.currentTimeMillis()}"
        val packDirectory = StickerRepository.packDirectory(appContext, identifier)
        if (!packDirectory.exists() && !packDirectory.mkdirs()) {
            error("Cannot create sticker directory: ${packDirectory.absolutePath}")
        }

        val stickers = imageUris.take(MAX_STICKERS_PER_PACK).mapIndexed { index, uri ->
            val sticker = Sticker(
                imageFileName = "sticker_${index + 1}.webp",
                emojis = listOf("\uD83D\uDE42"),
                accessibilityText = "A custom sticker selected from the phone."
            )
            val target = File(packDirectory, sticker.imageFileName)
            loadAndEncodeSticker(appContext, uri, target)
            sticker
        }

        val firstSticker = File(packDirectory, stickers.first().imageFileName)
        createTrayIcon(firstSticker, File(packDirectory, StickerRepository.TRAY_IMAGE_FILE))
        StickerRepository.createPack(appContext, identifier, packName, stickers).also { pack ->
            StickerRepository.savePack(appContext, pack)
        }
    }

    private fun loadAndEncodeSticker(context: Context, uri: Uri, outputFile: File) {
        val downloaded = try {
            Glide.with(context).downloadOnly().load(uri).submit().get()
        } catch (error: Exception) {
            Log.e(TAG, "Failed to load sticker image from $uri", error)
            throw IllegalStateException("Cannot load selected sticker image.", error)
        }

        val source = BitmapFactory.decodeFile(downloaded.absolutePath)
            ?: throw IllegalStateException("Selected image cannot be decoded.")
        val stickerBitmap = source.toStickerBitmap(WHATSAPP_STICKER_SIZE)
        source.recycle()

        var encoded = false
        var quality = 90
        while (quality >= 20 && !encoded) {
            writeBitmap(stickerBitmap, outputFile, webpFormat(), quality)
            encoded = outputFile.length() <= MAX_STATIC_STICKER_BYTES
            if (!encoded) {
                quality -= 10
            }
        }
        stickerBitmap.recycle()

        if (!encoded) {
            Log.e(TAG, "Sticker ${outputFile.name} is larger than WhatsApp limit: ${outputFile.length()} bytes")
            throw IllegalStateException("Encoded sticker is too large for WhatsApp: ${outputFile.name}")
        }
    }

    private fun createTrayIcon(stickerFile: File, trayFile: File) {
        val source = BitmapFactory.decodeFile(stickerFile.absolutePath)
            ?: throw IllegalStateException("Cannot decode prepared sticker for tray icon.")
        val trayBitmap = source.toStickerBitmap(WHATSAPP_TRAY_SIZE)
        source.recycle()
        writeBitmap(trayBitmap, trayFile, Bitmap.CompressFormat.PNG, 100)
        trayBitmap.recycle()
        if (trayFile.length() > MAX_TRAY_BYTES) {
            Log.w(TAG, "Tray icon is larger than recommended: ${trayFile.length()} bytes")
        }
    }

    private fun Bitmap.toStickerBitmap(size: Int): Bitmap {
        val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        canvas.drawColor(Color.TRANSPARENT)
        val scale = minOf(size.toFloat() / width, size.toFloat() / height)
        val scaledWidth = width * scale
        val scaledHeight = height * scale
        val left = (size - scaledWidth) / 2f
        val top = (size - scaledHeight) / 2f
        val dest = RectF(left, top, left + scaledWidth, top + scaledHeight)
        canvas.drawBitmap(this, null, dest, Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG))
        return output
    }

    private fun writeBitmap(
        bitmap: Bitmap,
        file: File,
        format: Bitmap.CompressFormat,
        quality: Int
    ) {
        FileOutputStream(file).use { output ->
            if (!bitmap.compress(format, quality, output)) {
                throw IllegalStateException("Failed to encode ${file.name}")
            }
        }
    }

    private fun webpFormat(): Bitmap.CompressFormat =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Bitmap.CompressFormat.WEBP_LOSSY
        } else {
            @Suppress("DEPRECATION")
            Bitmap.CompressFormat.WEBP
        }

    private companion object {
        const val TAG = "StickerImageProcessor"
        const val WHATSAPP_STICKER_SIZE = 512
        const val WHATSAPP_TRAY_SIZE = 96
        const val MAX_STATIC_STICKER_BYTES = 100 * 1024
        const val MAX_TRAY_BYTES = 50 * 1024
        const val MIN_STICKERS_PER_PACK = 3
        const val MAX_STICKERS_PER_PACK = 30
    }
}
