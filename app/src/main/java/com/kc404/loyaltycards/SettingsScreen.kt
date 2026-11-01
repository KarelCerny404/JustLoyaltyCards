package com.kc404.loyaltycards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Button(
            onClick = { clearAllInternalData(context) },
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                "Clear all internal data"
            )
        }

        Button(
            onClick = { navController.navigate(Screen.About.route) },
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                "About"
            )
        }
    }
}