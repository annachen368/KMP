package io.github.annachen368.kmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.annachen368.kmp.food.FoodScreen
import io.github.annachen368.kmp.food.FoodViewModel
import io.github.annachen368.kmp.food.FoodViewModel.Companion.foodViewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(topBar = { TopAppBar(title = { Text(text = "Food Items") }) }) { innerPadding ->
            val foodViewModel: FoodViewModel = viewModel(factory = foodViewModelFactory)
            val foodUiState by foodViewModel.uiState.collectAsState()
            FoodScreen(modifier = Modifier.padding(innerPadding), foodUiState)
        }
    }
}