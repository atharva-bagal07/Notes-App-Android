package com.example.notes.navigation

sealed class Routes(val route: String) {
    data object HomeScreenRoute : Routes("HomeScreen")
    data object AddNotesScreenRoute : Routes("AddNotesScreen")
}