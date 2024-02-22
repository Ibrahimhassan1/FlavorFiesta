package com.softups.flavorfiesta.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.softups.flavorfiesta.data.remote.dto.RecipeDto

@Database(
    entities = [RecipeDto::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract val recipeDao: RecipesDao

    companion object {
        const val DATABASE_NAME = "flavor_fiesta_db"
    }
}