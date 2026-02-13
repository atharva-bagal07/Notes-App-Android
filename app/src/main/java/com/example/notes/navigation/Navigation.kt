package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.uistate.screens.AddNote
import com.example.notes.uistate.screens.HomeScreen
import com.example.notes.viewmodel.NotesViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()


    val viewModel: NotesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreenRoute.route
    ) {

        composable(route = Routes.HomeScreenRoute.route) {
            HomeScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate(Routes.AddNotesScreenRoute.route)
                }
            )
        }

        composable(Routes.AddNotesScreenRoute.route) {
            AddNote(
                viewModel = viewModel,
                onBack = {
                    navController.navigate(Routes.HomeScreenRoute.route)
                }
            )
        }
    }
}
