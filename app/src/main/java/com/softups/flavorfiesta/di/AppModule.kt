package com.softups.flavorfiesta.di

import android.util.Log
import com.softups.flavorfiesta.common.Constants.BASE_URL
import com.softups.flavorfiesta.common.Constants.OK_HTTP_CLIENT_TIME_OUT
import com.softups.flavorfiesta.data.remote.RecipeApi
import com.softups.flavorfiesta.data.remote.repository.DefaultRecipeRepository
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
    fun provideRecipeRepository(recipeApi: RecipeApi): RecipeRepository {
        return DefaultRecipeRepository(recipeApi)
    }

}