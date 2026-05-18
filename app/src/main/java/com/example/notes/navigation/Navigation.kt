package com.example.notes.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.ui.screens.AddNote
import com.example.notes.ui.screens.EditScreen
import com.example.notes.ui.screens.HomeScreen
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
                },
                onNoteClick = { noteId ->
                    navController.navigate("edit/$noteId")
                }
            )
        }

        composable(
            route = "edit/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType }
            ),
            enterTransition = {
                scaleIn(initialScale = 0.9f) + fadeIn()
            },
            exitTransition = {
                scaleOut(targetScale = 0.9f) + fadeOut()
            }
        )
        { backStackEntry ->

            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0

            EditScreen(
                viewModel = viewModel,
                onEditComplete = {
                    navController.popBackStack()
                },
                id = noteId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.AddNotesScreenRoute.route) {
            AddNote(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}
