package com.kc404.loyaltycards

sealed class Screen(val route: String) {
    object Main : Screen("Main")
    object Add : Screen("Add")
    object Card : Screen("Card/{imageFilename}") {
        fun createRoute(imageFilename: String) = "card/$imageFilename"
    }
}