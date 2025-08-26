package io.github.annachen368.kmp.food

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FoodScreen(modifier: Modifier = Modifier, foodUiState: FoodUiState) {
    when (foodUiState) {
        is FoodUiState.Error -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = foodUiState.message)
            }
        }

        FoodUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is FoodUiState.Success -> {
            LazyColumn(modifier) {
                items(foodUiState.list) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = it.name, Modifier.weight(1f))
                        Text(text = "${it.calories} calories")
                    }
                }
            }
        }
    }
}