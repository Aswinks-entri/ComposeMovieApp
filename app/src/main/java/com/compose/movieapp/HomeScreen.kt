package com.compose.movieapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.movieapp.Constants.POSTER_BASE_URL
import com.compose.movieapp.data.Movies
import com.compose.movieapp.ui.theme.MovieAppComposeTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val moviesListFlow = homeViewModel.moviesListFlow.collectAsState()


    Surface(Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxWidth())
           {
            TopAppBar(
                title = {
                    Text(text = "Movies Compose")
                }
            )

            LaunchedEffect(Unit) {
                homeViewModel.getAllMovies()
            }

            moviesListFlow.value?.let {
                when (it) {
                    is Resource.Success -> {
                        val movies = it.result
                        movies?.let {
                            MovieList(movies = it,navController)
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
fun MovieList(movies:List<Movies?>,navController: NavController) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.padding(5.dp)){
        items(movies){ movie->
            movie?.let {
                MoviesItemCard(movieData = it, navController = navController )
            }

        }
    }
}

@Composable
fun MoviesItemCard(movieData:Movies,navController: NavController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            navController.navigate(Screens.Detail.passId(movieData.id))
        }) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(POSTER_BASE_URL+movieData.poster_path)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieAppComposeTheme {
        HomeScreen(navController = rememberNavController())
    }
}