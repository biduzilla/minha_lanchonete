package com.ricky.minhaempresa.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ricky.minhaempresa.presentation.home.MainScreen
import com.ricky.minhaempresa.presentation.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavAnimated() {
    val navControler = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navControler,
        startDestination = Screens.SplashScreen.route
    ) {
        composableSlideHorizontally(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navControler)
        }
        composableSlideHorizontally(route = Screens.HomeScreen.route) {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableFade(
    route: String,
    duration: Int = 600, // 200 - 600
    enterAlpha: Float = 0f, // 1f - 0f
    exitAlpha: Float = 0f,
    popEnterAlpha: Float = 0f,
    popExitAlpha: Float = 0f,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { fadeIn(tween(duration), enterAlpha) },
        exitTransition = { fadeOut(tween(duration), exitAlpha) },
        popEnterTransition = { fadeIn(tween(duration), popEnterAlpha) },
        popExitTransition = { fadeOut(tween(duration), popExitAlpha) },
        content = { screen() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 700, // 300 - 1000
    exitOffset: Int = -700,
    popEnterOffset: Int = -700,
    popExitOffset: Int = 700,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)) { exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}
