package com.example.cocktailfinder.data.remote

import com.example.cocktailfinder.data.response.CocktailList
import com.example.cocktailfinder.data.response.Drink
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php?")
    fun getCocktails(@Query("s") name: String): Call<CocktailList>
    @GET("lookup.php?")
    fun getCocktail(@Query("i") id: String): Call<CocktailList>
}
