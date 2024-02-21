package com.softups.flavorfiesta.di

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

}