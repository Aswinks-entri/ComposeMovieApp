package com.compose.movieapp

sealed class Screens(val route:String) {
    object Login:Screens(route = "login_screen")
    object SignUp:Screens(route = "signup_screen")
    object Home:Screens(route = "home_screen")
    object Detail:Screens(route = "detail_screen/{id}"){
        fun passId(id:Int):String{
           // return this.route.replace(oldValue = "id", newValue = id.toString())
            return "detail_screen/$id"
        }
    }
}