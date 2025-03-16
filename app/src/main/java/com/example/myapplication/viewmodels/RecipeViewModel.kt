package com.example.myapplication.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Recipe
import com.example.myapplication.models.RecipeApi
import kotlinx.coroutines.launch

class RecipeViewModel: ViewModel() {
    var recipes by mutableStateOf<List<Recipe>?>(null)
    var error by mutableStateOf("")
    var loading by mutableStateOf(true)

    fun getRecipes(query: String) {
        viewModelScope.launch{
            var recipeApi: RecipeApi? = null

            try {
                recipeApi = RecipeApi.getInstance()
                recipes = recipeApi.getRecipes(query)
                loading = false
                Log.d("RECIPE",recipes.toString())
            } catch(e: Exception) {
                error = e.message.toString()
                Log.d("ERROR",e.message.toString())
            }
        }
    }
}