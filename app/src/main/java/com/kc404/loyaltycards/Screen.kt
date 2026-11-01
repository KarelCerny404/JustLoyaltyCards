package com.kc404.loyaltycards

sealed class Screen(val route: String) {
    object Main : Screen("Main")
    object Add : Screen("Add/{editCardName}") {
        fun createRoute(editCardName: String) = "add/$editCardName"
    }

    object Card : Screen("Card/{cardName}/{imageFilename}") {
        fun createRoute(imageFilename: String, cardName: String) = "card/$cardName/$imageFilename"
    }

    object Settings : Screen("Settings")

    object About : Screen("About")
}