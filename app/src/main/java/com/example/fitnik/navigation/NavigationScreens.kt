package com.example.fitnik.navigation

import kotlinx.serialization.Serializable

sealed class NavigationScreens {

    @Serializable
    object Home: NavigationScreens()

    @Serializable
    object Onboarding: NavigationScreens()

    @Serializable
    object Login: NavigationScreens()

    @Serializable
    object SignUp: NavigationScreens()
}