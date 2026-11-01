package com.kc404.loyaltycards

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

fun createDataStoreFile(context: Context) {
    val filesDir = context.filesDir
    val dataStoreFile = File(filesDir, "cards.json")

    dataStoreFile.createNewFile()

    val imageStoreDirectory = File(filesDir, "img")
    imageStoreDirectory.mkdirs()
}

fun checkDataStoreFileExistence(context: Context): Boolean {
    val filesDir = context.filesDir
    val dataStoreFile = File(filesDir, "cards.json")

    return dataStoreFile.exists()
}

fun cardCreate(context: Context, cardName: String, cardImageUri: Uri?) {
    val filesDir = context.filesDir
    val dataStoreFile = File(filesDir, "cards.json")

    val dataJsonObject = try {
        val dataJsonString = dataStoreFile.readText()
        JSONObject(dataJsonString)
    } catch (_: Exception) {
        JSONObject()
    }

    val cardsArray = if (dataJsonObject.has("cards")) {
        dataJsonObject.getJSONArray("cards")
    } else {
        JSONArray()
    }

    var alreadyExists = false
    for (i in 0 until cardsArray.length()) {
        if (cardsArray.getString(i) == cardName) {
            alreadyExists = true
            break
        }
    }

    if (!alreadyExists) {
        cardsArray.put(cardName)
    }

    dataJsonObject.put("cards", cardsArray)
    val updatedDataJsonString = dataJsonObject.toString()
    dataStoreFile.writeText(updatedDataJsonString)

    if (cardImageUri != null) {
        val imageFileDest =
            File(filesDir, "img/$cardName.${cardImageUri.getFileExtension(context)}")
        val contentResolver = context.contentResolver
        contentResolver.openInputStream(cardImageUri)?.use { input ->
            FileOutputStream(imageFileDest).use { output ->
                input.copyTo(output)
            }
        }
    }
}

fun cardRead(context: Context): List<String> {
    val filesDir = context.filesDir
    val dataStoreFile = File(filesDir, "cards.json")

    val dataJsonObject = try {
        val dataJsonString = dataStoreFile.readText()
        JSONObject(dataJsonString)
    } catch (_: Exception) {
        JSONObject()
    }

    if (!dataJsonObject.has("cards")) {
        return emptyList()
    }

    val jsonArray: JSONArray = dataJsonObject.getJSONArray("cards")

    return List(jsonArray.length()) { i ->
        jsonArray.getString(i)
    }
}

fun cardUpdate(
    context: Context,
    cardName: String,
    newCardName: String? = null,
    newCardImage: Uri? = null
) {
    if (newCardName != null && newCardImage == null) {
        cardDelete(context, cardName)
        cardCreate(context, newCardName, null)
        File(context.filesDir, "img/$cardName.jpg").copyTo(
            File(
                context.filesDir,
                "img/$newCardName.jpg"
            )
        )
    }

    if (newCardName != null && newCardImage != null) {
        cardDelete(context, cardName)
        cardCreate(context, newCardName, newCardImage)
    }

    if (newCardName == null && newCardImage != null) {
        cardDelete(context, cardName)
        cardCreate(context, cardName, newCardImage)
    }
}

fun cardDelete(context: Context, cardName: String) {
    val filesDir = context.filesDir
    val dataStoreFile = File(filesDir, "cards.json")
    val cardImage = File(filesDir, "img/$cardName.jpg")

    val dataJsonObject = try {
        val dataJsonString = dataStoreFile.readText()
        JSONObject(dataJsonString)
    } catch (_: Exception) {
        JSONObject()
    }

    val cardsArray = if (dataJsonObject.has("cards")) {
        dataJsonObject.getJSONArray("cards")
    } else {
        JSONArray()
    }

    for (i in 0 until cardsArray.length()) {
        if (cardsArray.getString(i) == cardName) {
            cardsArray.remove(i)
            cardImage.delete()
            break
        }
    }

    dataJsonObject.put("cards", cardsArray)
    val updatedJsonDataString = dataJsonObject.toString()
    dataStoreFile.writeText(updatedJsonDataString)
}

fun clearAllInternalData(context: Context) {
    File(context.filesDir, "cards.json").delete()
    File(context.filesDir, "img").deleteRecursively()
}

private fun Uri.getFileExtension(context: Context): String {
    val mime = context.contentResolver.getType(this) ?: return "jpg"
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(mime) ?: "jpg"
}
