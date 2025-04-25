package com.example.fitnik.navigation

import kotlinx.serialization.Serializable

sealed class NavigationGraph {
    sealed class AuthGraph : NavigationGraph() {
        @Serializable
        object Onboarding : AuthGraph()

        @Serializable
        object Login : AuthGraph()

        @Serializable
        object SignUp : AuthGraph()

        @Serializable
        object UserProfileSetUp : AuthGraph()
    }

    sealed class MainGraph : NavigationGraph() {
        @Serializable
        object Home : MainGraph()

        @Serializable
        object CreateWorkout : MainGraph()

        sealed class HomeTabsGraph : MainGraph() {
            @Serializable
            object Home : HomeTabsGraph()

            @Serializable
            object StepsScreen : HomeTabsGraph()

            @Serializable
            object Settings : HomeTabsGraph()

            @Serializable
            object Timer : HomeTabsGraph()

            @Serializable
            data class RoutineDetail(val id: String) : HomeTabsGraph()
        }
    }
}