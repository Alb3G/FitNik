package com.example.fitnik.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.fitnik.core.presentation.FitnikDefButton
import com.github.barteksc.pdfviewer.PDFView


@Composable
fun PDFTermsScreen(
    fileName: String,
    onDisplay: () -> Unit
) {
    val buttonTextStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                PDFView(it, null).apply {
                    fromAsset(fileName)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .load()
                }
            },
            update = {
                it.zoomTo(1.5f)
                it.invalidate()
            }
        )

        FitnikDefButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            onAction = onDisplay
        ) {
            Text("Back", style = buttonTextStyle)
        }
    }
}