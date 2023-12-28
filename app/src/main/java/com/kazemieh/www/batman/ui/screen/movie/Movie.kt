package com.kazemieh.www.batman.ui.screen.movie

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import com.kazemieh.www.batman.ui.theme.gray
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Movie(
    navController: NavHostController,
    imdbID: String,
    viewModel: MovieViewModel = hiltViewModel()
) {
    var movie by remember {
        mutableStateOf(Movie())
    }

    LaunchedEffect(key1 = true) {
        viewModel.getMovieById(id = imdbID)
        viewModel.getMovieById.collectLatest { movieResult ->
            Log.d("949494", "AmazingOfferSection: $movieResult")
            when (movieResult) {
                is ApiResult.Success -> {
                    movie = movieResult.data
//                    Log.d("TAG", "AmazingOfferSection: $amazingItemList")
                }

                is ApiResult.Error -> {
//                    Log.d("TAG", "AmazingOfferSection: ${movieResult} ")
                }

                is ApiResult.Loading -> {
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(model = movie.Poster),
                contentDescription = movie.Title
            )
        }
        item { movie.Title?.let { Text(text = it) } }
        item { movie.Plot?.let { Text(text = it) } }


        item { movie.Title?.let { Detail("Title", it) } }
        item { movie.Title?.let { Detail("Title", it) } }
        item { movie.Title?.let { Detail("Title", it) } }
        item { movie.Title?.let { Detail("Title", it) } }
        item { movie.Title?.let { Detail("Title", it) } }
        item { movie.Title?.let { Detail("Title", it) } }

    }
}

@Composable
fun Detail(key: String, value: String) {
    Row {
        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = key,
                fontWeight = FontWeight.Medium,
            )
        }

        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colorScheme.gray)
            )
        }
    }
}