package com.example.whatapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.whatapp.sticker.StickerImageProcessor
import com.example.whatapp.sticker.StickerPack
import com.example.whatapp.sticker.StickerRepository
import com.example.whatapp.sticker.WhitelistCheck
import com.example.whatapp.ui.theme.WhatAPPTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StickerDemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun StickerDemoScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isPreparing by remember { mutableStateOf(false) } // app có đang xử lý ảnh không
    var selectedImages by remember { mutableStateOf<List<Uri>>(emptyList()) } //danh sách ảnh người dùng chọn.
    var preparedPack by remember { mutableStateOf<StickerPack?>(StickerRepository.latestPreparedPack(context)) } //pack đã tạo xong hay chưa.
    var packName by remember { mutableStateOf(preparedPack?.name ?: "My Sticker Pack") } //tên pack người dùng nhập
    var status by remember { mutableStateOf(packStatus(preparedPack)) } //dòng thông báo trên màn hình

    val addStickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val pack = preparedPack
        status = if (pack != null && (result.resultCode == Activity.RESULT_OK || WhitelistCheck.isWhitelisted(context, pack.identifier))) {
            "Added or already available in WhatsApp."
        } else {
            "WhatsApp did not add the pack. Make sure WhatsApp is installed, logged in, and supports stickers."
        }
    }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents() //GetMultipleContents() mở trình chọn file/ảnh của Android
    ) { uris ->
        selectedImages = uris.take(30)
        status = if (selectedImages.size >= 3) {
            "Selected ${selectedImages.size} images. Now create the sticker pack."
        } else {
            "Select at least 3 images. WhatsApp requires 3-30 stickers per pack."
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "WhatsApp Sticker Demo",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        //Preview Ảnh Bằng Glide
        AndroidView(
            modifier = Modifier.size(220.dp),
            factory = { viewContext ->
                ImageView(viewContext).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
            },
            //Glide sẽ load ảnh đầu tiên người dùng chọn vào ImageView
            update = { imageView ->
                Glide.with(imageView)
                    .load(selectedImages.firstOrNull())
                    .into(imageView)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = packName,
            onValueChange = { packName = it },
            label = { Text("Pack name") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isPreparing,
            onClick = { imagePickerLauncher.launch("image/*") }
        ) {
            Text("Choose Photos From Phone")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isPreparing && selectedImages.size >= 3,
            onClick = {
                isPreparing = true
                status = "Processing selected photos into a new sticker pack..."
                scope.launch {
                    try {
                        preparedPack = StickerImageProcessor(context)
                            .preparePackFromPhoneImages(selectedImages, packName)
                        status = "Ready. Created '${preparedPack?.name}' with ${preparedPack?.stickers?.size ?: 0} stickers."
                    } catch (error: Exception) {
                        status = error.message ?: "Failed to prepare sticker pack."
                    } finally {
                        isPreparing = false
                    }
                }
            }
        ) {
            Text(if (isPreparing) "Creating..." else "Create New Sticker Pack")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = preparedPack != null && !isPreparing,
            onClick = {
                val pack = preparedPack
                if (pack == null || !StickerRepository.isPrepared(context, pack)) {
                    status = "Create a sticker pack before adding it to WhatsApp."
                    return@Button
                }
                if (!WhitelistCheck.isAnyWhatsAppInstalled(context)) {
                    status = "WhatsApp is not installed. Install WhatsApp, log in, then try again."
                    return@Button
                }
                if (WhitelistCheck.isWhitelisted(context, pack.identifier)) {
                    status = "Sticker pack is already added to WhatsApp."
                    return@Button
                }

                val intent = Intent("com.whatsapp.intent.action.ENABLE_STICKER_PACK").apply {
                    putExtra("sticker_pack_id", pack.identifier)
                    putExtra("sticker_pack_authority", StickerRepository.contentProviderAuthority())
                    putExtra("sticker_pack_name", pack.name)
                }
                try {
                    addStickerLauncher.launch(intent)
                } catch (_: ActivityNotFoundException) {
                    Toast.makeText(context, "WhatsApp cannot handle this request.", Toast.LENGTH_LONG).show()
                    status = "WhatsApp cannot handle this request. Check install/login state."
                }
            }
        ) {
            Text("Add to WhatsApp")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = status,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun packStatus(pack: StickerPack?): String =
    if (pack == null) {
        "Choose at least 3 photos, then create a new sticker pack."
    } else {
        "Latest pack ready: '${pack.name}'. You can add it to WhatsApp."
    }
