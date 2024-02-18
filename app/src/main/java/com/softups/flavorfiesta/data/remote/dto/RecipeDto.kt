package com.softups.flavorfiesta.data.remote.dto

import com.softups.flavorfiesta.domain.model.Recipe

data class RecipesDto(
    val recipes: List<RecipeDto>,
    val total: Int
)
data class RecipeDto(
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
    val userId: Int,
    val image: String,
    val rating: Double,
    val reviewCount: Int,
    val mealType: List<String>
)

fun RecipeDto.toRecipe() = Recipe(
    id = id,
    name = name,
    ingredients = ingredients,
    instructions = instructions,
    prepTimeMinutes= prepTimeMinutes,
    cookTimeMinutes = cookTimeMinutes,
    caloriesPerServing = caloriesPerServing,
    servings = servings,
    difficulty = difficulty,
    cuisine = cuisine,
    tags = tags,
    image = image
)