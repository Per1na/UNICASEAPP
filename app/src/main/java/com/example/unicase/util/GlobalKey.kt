package com.example.unicase.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class GlobalKey {
    val id = UUID.randomUUID().toString()
    fun modifier() = Modifier

    suspend fun captureBitmap(
        context: Context,
        content: @Composable () -> Unit,
        widthPx: Int = 720,
        heightPx: Int = 1440
    ): Bitmap = withContext(Dispatchers.Main) {
        val view = ComposeView(context).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { content() }
        }

        view.measure(
            View.MeasureSpec.makeMeasureSpec(widthPx, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(heightPx, View.MeasureSpec.EXACTLY)
        )
        view.layout(0, 0, widthPx, heightPx)

        val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return@withContext bitmap
    }
}

suspend fun captureAndSaveDesign(
    context: Context,
    key: GlobalKey,
    content: @Composable () -> Unit
): File = withContext(Dispatchers.IO) {
    val bitmap = key.captureBitmap(context, content)
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "Design_$timestamp.png"
    val path = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)

    FileOutputStream(path).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }

    return@withContext path
}