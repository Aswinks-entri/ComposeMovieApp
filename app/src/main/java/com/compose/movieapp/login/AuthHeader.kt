package com.compose.movieapp.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.movieapp.ui.theme.MovieAppComposeTheme
import com.compose.movieapp.R
import com.compose.movieapp.ui.theme.spacing


@Composable
fun AuthHeader() {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = androidx.compose.material3.MaterialTheme.spacing

        Image(
            modifier = Modifier
                .size(200.dp, 200.dp)
                .wrapContentHeight()
                .padding(top = spacing.medium),
            painter = painterResource(id = R.drawable.auth_icon),
            contentDescription = null
        )

        Text(
            text = "Welcome to movies app",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = spacing.small),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold

        )

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppHeaderLight() {
    MovieAppComposeTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            AuthHeader()
        }

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHeaderDark() {
    MovieAppComposeTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            AuthHeader()
        }
    }
}