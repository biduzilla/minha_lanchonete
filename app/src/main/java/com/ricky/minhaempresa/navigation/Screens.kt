package com.ricky.minhaempresa.navigation

sealed class Screens(val route:String){
    object SplashScreen:Screens("splash")
    object HomeScreen:Screens("home")
}
