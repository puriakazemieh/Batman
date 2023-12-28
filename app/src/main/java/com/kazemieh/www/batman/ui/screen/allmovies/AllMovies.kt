package com.kazemieh.www.batman.ui.screen.allmovies

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.kazemieh.www.batman.R
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.navigation.Screen
import com.kazemieh.www.batman.ui.theme.cardBackground
import com.kazemieh.www.batman.ui.theme.white60
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AllMovies(
    navController: NavHostController,
    viewModel: AllMoviesViewModel = hiltViewModel()
) {

    var allMovesList by remember {
        mutableStateOf<List<AllMoves>>(emptyList())
    }

    LaunchedEffect(key1 = true) {
        viewModel.getAllMovies.collectLatest { allMovesListResult ->
            Log.d("949494", "AmazingOfferSection: $allMovesListResult")
            when (allMovesListResult) {
                is ApiResult.Success -> {
                    allMovesList = allMovesListResult.data
//                    Log.d("TAG", "AmazingOfferSection: $amazingItemList")
                }

                is ApiResult.Error -> {
//                    Log.d("TAG", "AmazingOfferSection: ${allMovesListResult} ")
                }

                is ApiResult.Loading -> {
                }
            }
        }
    }


    LazyColumn(
        modifier = Modifier.background(colorScheme.surface)
    ) {
        items(allMovesList) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        it.imdbID?.let { it1 -> navController.navigate(Screen.Movie.withArgs(it1)) }
                    }
                    .height(300.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = colorScheme.cardBackground),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(colorScheme.surface)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            it.poster,
                            placeholder = painterResource(id = R.drawable.ic_launcher_background)
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.FillWidth,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorScheme.white60)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            it.title?.let { it1 ->
                                Text(
                                    modifier = Modifier.weight(0.85f),
                                    text = it1,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            it.year?.let { it1 ->
                                Text(
                                    text = it1,
                                    modifier = Modifier.weight(0.15f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}