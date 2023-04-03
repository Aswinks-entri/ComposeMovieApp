package com.compose.movieapp.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.movieapp.Screens
import com.compose.movieapp.ui.theme.MovieAppComposeTheme

@Composable
fun LoginScreen(
    navController:NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthHeader()
        
        Spacer(modifier = Modifier.size(50.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it
        },
            label = { Text(text = "Email", color = MaterialTheme.colorScheme.secondary) },
            placeholder = { Text(text = "example@gmail.com") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "emailIcon"
                )
            },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                autoCorrect = false
            )
        )

        OutlinedTextField(value = password, onValueChange = {
            password = it
        },
            label = { Text(text = "Password", color = MaterialTheme.colorScheme.secondary) },
            placeholder = { Text(text = "") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "passwordIcon"
                )
            },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                autoCorrect = false
            )
        )

        Button(
            onClick = {
                navController.navigate(route = Screens.Home.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Do not have an account? Click here to sign up",
            color = Color.Black,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .clickable {
                    navController.navigate(route = Screens.SignUp.route)
                }
        )

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppComposeTheme {
       LoginScreen(navController = rememberNavController())
    }
}