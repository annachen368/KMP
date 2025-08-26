package io.github.annachen368.kmp.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.annachen368.kmp.model.FoodItem
import kmp.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.InternalResourceApi

class FoodViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<FoodUiState> = MutableStateFlow(FoodUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        showFoodItems()
    }

    fun showFoodItems() {
        viewModelScope.launch {
            val list = loadFoodFromResource()
            if (list.isNotEmpty()) {
                _uiState.update { FoodUiState.Success(list) }
            } else {
                _uiState.update { FoodUiState.Error("Error") }
            }
        }
    }

    suspend fun loadFoodFromResource(): List<FoodItem> {
        val bytes = Res.readBytes("files/food_nutrition.json")
        val text = bytes.decodeToString()
        return Json.decodeFromString<List<FoodItem>>(text)
    }

    companion object {
        val foodViewModelFactory = viewModelFactory {
            initializer {
                FoodViewModel()
            }
        }
    }
}

sealed interface FoodUiState {
    object Loading : FoodUiState
    class Success(val list: List<FoodItem>) : FoodUiState
    class Error(val message: String) : FoodUiState
}