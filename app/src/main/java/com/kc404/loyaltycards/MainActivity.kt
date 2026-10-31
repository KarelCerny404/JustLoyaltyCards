package com.kc404.loyaltycards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kc404.loyaltycards.ui.theme.JustLoyaltyCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustLoyaltyCardsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { MainScreen(navController, modifier) }
        composable(
            Screen.Add.route
        ) { backStackEntry ->
            AddScreen(
                navController,
                modifier,
                backStackEntry.arguments?.getString("editCardName") ?: ""
            )
        }
        composable(
            Screen.Card.route
        ) { backStackEntry ->
            CardScreen(
                navController,
                cardName = backStackEntry.arguments?.getString("cardName") ?: "",
                imageFilename = backStackEntry.arguments?.getString("imageFilename") ?: "",
                modifier
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JustLoyaltyCardsTheme {
        Main()
    }
}