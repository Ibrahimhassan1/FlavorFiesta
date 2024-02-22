package com.softups.flavorfiesta.di

import android.app.Application
import androidx.room.Room
import com.softups.flavorfiesta.data.local.RecipesDao
import com.softups.flavorfiesta.data.local.RecipesDatabase
import com.softups.flavorfiesta.domain.repository.RecipeRepository
import com.softups.flavorfiesta.repository.TestRecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(): RecipeRepository {
        return TestRecipesRepository()
    }

    @Provides
    @Singleton
    fun provideRecipesDatabase(app: Application): RecipesDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            RecipesDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(recipesDatabase: RecipesDatabase): RecipesDao {
        return recipesDatabase.recipeDao
    }
}