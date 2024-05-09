package com.example.cocktailfinder.cocktailList

import com.example.cocktailfinder.data.remote.CocktailApi
import com.example.cocktailfinder.data.response.CocktailList

class CocktailListRepoImp(private val api: CocktailApi):CocktailListRepo {
    override suspend fun getCocktails(name: String): CocktailList {
           return api.getCocktails(name).execute().body()!!
    }

    override suspend fun getCocktail(id: String): CocktailList {
        return api.getCocktail(id).execute().body()!!
    }
}