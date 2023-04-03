package com.compose.movieapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.movieapp.Constants.POSTER_BASE_URL
import com.compose.movieapp.data.MovieResponseDetail
import kotlin.math.roundToInt

@Composable
fun DetailScreen(
    navController: NavController,
    movieId: Int,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val moviesDetailFlow = viewModel.moviesDetailFlow.collectAsState()

    Surface(Modifier.fillMaxSize(), color = Color.Black) {

        Column(modifier = Modifier.fillMaxWidth())
        {
            TopAppBar(
                navigationIcon = {
                      IconButton(onClick = { navController.popBackStack() }) {
                         Icon(Icons.Filled.ArrowBack, "backIcon")
                      }           
                },
                title = {
                    Text(text = "Movies Compose")
                }
            )
            LaunchedEffect(Unit) {
                viewModel.getMovieDetail(movieId)
            }

            moviesDetailFlow.value?.let {
                when (it) {
                    is Resource.Success -> {
                        val movies = it.result
                        movies?.let {
                           movieDatas(movieResponseDetail = it)
                        }

                    }

                    is Resource.Loading ->{

                    }

                    is Resource.Failure -> {

                    }

                    else -> {}
                }
            }




        }
    }
}

@Composable
fun movieDatas(movieResponseDetail: MovieResponseDetail) {

    Column {

        HeaderWithImage(image = POSTER_BASE_URL+movieResponseDetail.poster_path)

        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(8f)) {
                Text(
                    text = movieResponseDetail.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )

                Text(
                    text = "Release Date : ${movieResponseDetail.release_date}",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }

            Card(
                backgroundColor = Color.Gray,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.weight(2f)
            ) {
                Row(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "${movieResponseDetail.vote_average.roundToInt()}",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(vertical = 0.dp, horizontal = 0.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

            }
        }

        LinearProgressIndicator(
            progress = 0.7f,
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .align(CenterHorizontally),
            backgroundColor = Color.Gray
        )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 15.dp)
                .align(CenterHorizontally)


        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    text = "Continue Watching",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                )
            }

        }

        Text(
            text = movieResponseDetail.overview,
            color = Color.White,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)
        )
    }
}

@Composable
fun HeaderWithImage(image: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    )
}

