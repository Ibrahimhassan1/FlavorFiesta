package com.softups.flavorfiesta.domain.model

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val caloriesPerServing: Int,
    val servings: Int,
    val difficulty: String,
    val cuisine: String,
    val tags: List<String>,
    val image: String,
)
