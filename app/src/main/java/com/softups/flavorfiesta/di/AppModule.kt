package com.softups.flavorfiesta.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.softups.flavorfiesta.common.Constants.BASE_URL
import com.softups.flavorfiesta.common.Constants.OK_HTTP_CLIENT_TIME_OUT
import com.softups.flavorfiesta.data.local.LocalDataSource
import com.softups.flavorfiesta.data.local.RecipesDao
import com.softups.flavorfiesta.data.local.RecipesDatabase
import com.softups.flavorfiesta.data.remote.RecipeApi
import com.softups.flavorfiesta.data.remote.RemoteDataSource
import com.softups.flavorfiesta.data.repository.DefaultRecipeRepository
import com.softups.flavorfiesta.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi {
        val logging = HttpLoggingInterceptor { message ->
            Log.d(
                "OkHttp",
                message
            )
        }

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(OK_HTTP_CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(recipeApi: RecipeApi): RemoteDataSource {
        return RemoteDataSource(recipeApi)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(recipesDao: RecipesDao): LocalDataSource {
        return LocalDataSource(recipesDao)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): RecipeRepository {
        return DefaultRecipeRepository(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideRecipesDatabase(app: Application): RecipesDatabase {
        return Room.databaseBuilder(
            app,
            RecipesDatabase::class.java,
            RecipesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(recipesDatabase: RecipesDatabase): RecipesDao {
        return recipesDatabase.recipeDao
    }

}