package com.example.cocktailfinder.di

import com.example.cocktailfinder.cocktailDetail.CocktailDetailViewModel
import com.example.cocktailfinder.cocktailList.CocktailDetailScreen
import com.example.cocktailfinder.cocktailList.CocktailListRepo
import com.example.cocktailfinder.cocktailList.CocktailListRepoImp
import com.example.cocktailfinder.cocktailList.CocktailListViewModel
import com.example.cocktailfinder.data.remote.CocktailApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)
    }
    single<CocktailListRepo> { CocktailListRepoImp(get()) }
    viewModel<CocktailListViewModel> { CocktailListViewModel(get()) }
    viewModel<CocktailDetailViewModel> { parameters -> CocktailDetailViewModel(id = parameters.get(), get()) }
}