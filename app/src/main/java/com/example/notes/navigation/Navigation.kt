package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.uistate.screens.AddNote
import com.example.notes.uistate.screens.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreenRoute.route) {

        composable(route = Routes.HomeScreenRoute.route) {
            HomeScreen{
                navController.navigate(route = Routes.AddNotesScreenRoute.route)
            }
        }
        composable(Routes.AddNotesScreenRoute.route) {
            AddNote{
                navController.navigate(route = Routes.HomeScreenRoute.route)
            }
        }
    }
}