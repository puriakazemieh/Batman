package com.kazemieh.www.batman.navigation


sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")
    data object Movies : Screen("Movies_screen")
    data object Movie : Screen("Movie_screen")


    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}