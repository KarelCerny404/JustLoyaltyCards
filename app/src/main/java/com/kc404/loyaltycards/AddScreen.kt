package com.kc404.loyaltycards

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    editCardName: String = ""
) {
    val context = LocalContext.current
    var cardName by remember { mutableStateOf(editCardName) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    if (editCardName == "{editCardName}") {
        cardName = ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        TextField(
            value = cardName,
            onValueChange = { newText ->
                cardName = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text(text = "Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close"
                    )
                }
            }
        )
        imageUri = imageSelectorDemo()
        Button(
            onClick = {
                if (imageUri != null) {
                    if (checkDataStoreFileExistence(context)) {
                        cardCreate(context, cardName, imageUri as Uri)
                        navController.popBackStack()
                    } else {
                        createDataStoreFile(context)
                        cardCreate(context, cardName, imageUri as Uri)
                        navController.popBackStack()
                    }

                }
            },
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            if (editCardName == "" || editCardName == "{editCardName}") {
                Text(
                    "Create"
                )
            } else {
                Text(
                    "Save"
                )
            }
        }
    }
}