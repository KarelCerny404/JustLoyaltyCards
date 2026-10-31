package com.kc404.loyaltycards

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.File

@Composable
fun CardScreen(navController: NavController, imageFilename: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageFile = File(context.filesDir, "img/$imageFilename")

    val bitmap = remember(imageFile) {
        if (imageFile.exists()) {
            BitmapFactory.decodeFile(imageFile.absolutePath)
        } else {
            null
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = modifier
                .padding(horizontal = 12.dp)
                .size(56.dp),
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                disabledContentColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}