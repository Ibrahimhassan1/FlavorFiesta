package com.softups.flavorfiesta.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softups.flavorfiesta.data.remote.dto.RecipeDto

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): List<RecipeDto>

    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipe: List<RecipeDto>)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeDto)
}