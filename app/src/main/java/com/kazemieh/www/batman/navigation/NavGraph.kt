package com.kazemieh.www.batman.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kazemieh.www.batman.ui.screen.movie.Movie
import com.kazemieh.www.batman.ui.screen.allmovies.AllMovies
import com.kazemieh.www.batman.ui.screen.splash.SplashScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Splash.route ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Movies.route) {
            AllMovies(navController = navController)
        }

        composable(
            route = Screen.Movie.route + "/{imdbID}",
            arguments = listOf(
                navArgument("imdbID") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {

            it.arguments?.getString("imdbID")?.let { imdbID ->
                Movie(
                    navController = navController,
                    imdbID = imdbID
                )
            }
        }
    }
}