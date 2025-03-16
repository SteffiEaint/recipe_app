package com.example.myapplication.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

data class Recipe (
    var title: String,
    var ingredients: String,
    var instructions: String
)

const val BASE_URL = "https://api.api-ninjas.com/v1/"
const val API_KEY = "zMznzQ+9grr4Zufhy74DlA==wP1bqPU9NsmAbXuY"

interface RecipeApi {
    @Headers("X-Api-Key: $API_KEY")
    @GET("recipe")
    suspend fun getRecipes(@Query("query") query: String): List<Recipe>

    companion object {
        private var recipeService: RecipeApi? = null

        fun getInstance(): RecipeApi {
            if (recipeService === null) {
                recipeService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RecipeApi::class.java)
            }
            return recipeService!!
        }
    }
}