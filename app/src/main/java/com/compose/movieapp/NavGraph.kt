package com.compose.movieapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.compose.movieapp.login.LoginScreen
import com.compose.movieapp.login.SignUpScreen

@Composable
fun SetUpNavGraph(
    navController:NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ){
        composable(
            route = Screens.Login.route
        ){
            LoginScreen(navController)
        }
        composable(
            route = Screens.SignUp.route
        ){
            SignUpScreen(navController)
        }

        composable(
            route = Screens.Home.route
        ){
            HomeScreen(navController)
        }

        composable(
            route = Screens.Detail.route,
            arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
                defaultValue = -1
            }
        )){
            Log.e("Compose::", "SetUpNavGraph:--->${it.arguments?.getInt("id").toString()} ", )
            val movieId = requireNotNull(it.arguments?.getInt("id"))
            DetailScreen(navController,movieId)
        }
    }
    
}