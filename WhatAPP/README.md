# WhatsApp Sticker Demo

Minimal Android Kotlin app that creates a WhatsApp sticker pack from photos selected on the phone and opens the official WhatsApp sticker add flow. UI is intentionally simple; the important parts are Glide image loading, local WebP processing, the sticker `ContentProvider`, and the official WhatsApp intent.

## Environment

- Android Studio
- JDK 11
- Gradle Wrapper 8.10.2
- Android Gradle Plugin 8.8.2
- Kotlin 2.0.21
- compileSdk 36, targetSdk 36, minSdk 28
- Glide 5.0.5

## Run

1. Open this folder in Android Studio.
2. Sync Gradle.
3. Run the `app` configuration on an Android 9+ device or emulator.

## Test WhatsApp Flow

1. Install WhatsApp.
2. Log in to WhatsApp.
3. Open this app.
4. Enter a pack name.
5. Tap `Choose Photos From Phone`.
6. Select at least 3 images and at most 30 images.
7. Tap `Create New Sticker Pack`.
8. Wait for the status to become ready.
9. Tap `Add to WhatsApp`.
10. Confirm in the official WhatsApp UI.

The confirmation sheet is owned by WhatsApp. Its wording and state can differ by WhatsApp version, whether WhatsApp is installed, and whether the user is logged in.

## Error Cases

- If WhatsApp is not installed, the app shows a clear status message instead of launching the add flow.
- If WhatsApp is installed but cannot handle the request, for example because the user is not logged in or the installed version does not support stickers, the app shows a friendly error and does not crash.
- If fewer than 3 images are selected, the app asks for more images because WhatsApp requires 3-30 stickers per pack.
- If a selected image cannot be decoded or encoded small enough for WhatsApp, the status area shows the processing error.
- `Add to WhatsApp` stays disabled until the new sticker pack and tray icon are prepared.

## Implementation Notes

- Uses Glide `5.0.5` for both preview loading and reading selected image URIs.
- Does not use OkHttp or Retrofit directly for sticker downloads.
- Writes processed files into app-private storage under `files/stickers/custom_<timestamp>`.
- Stores each custom pack metadata in `pack.json` inside that pack directory.
- Exposes metadata and image files through `${applicationId}.stickercontentprovider` with read permission `com.whatsapp.sticker.READ`.
- Uses the official action `com.whatsapp.intent.action.ENABLE_STICKER_PACK` with `sticker_pack_id`, `sticker_pack_authority`, and `sticker_pack_name`.
