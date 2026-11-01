package com.kc404.loyaltycards

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }

        Text(
            "Just an app for store loyalty cards (or any images)\nMade with ❤️ by kc404",
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://github.com/KarelCerny404/JustLoyaltyCards".toUri()
                    )
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    "Source (github)"
                )
            }

            Button(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://github.com/KarelCerny404".toUri()
                    )
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    "My profile"
                )
            }
        }
    }
}