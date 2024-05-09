package com.example.cocktailfinder.cocktailList

import com.example.cocktailfinder.data.response.CocktailList

interface CocktailListRepo {
   suspend fun getCocktails(name:String):CocktailList;
   suspend fun getCocktail(id:String):CocktailList;
}
