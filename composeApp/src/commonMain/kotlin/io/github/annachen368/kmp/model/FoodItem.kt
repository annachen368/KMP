package io.github.annachen368.kmp.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodItem(
    val id: Int,
    val name: String,
    val calories: Int,
    val protein_g: Double,
    val fat_g: Double,
    val carbs_g: Double,
    val fiber_g: Double,
    val sugar_g: Double
)