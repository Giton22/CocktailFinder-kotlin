package com.example.cocktailfinder.cocktailList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailfinder.data.response.CocktailList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CocktailListViewModel(private val cocktailListRepo: CocktailListRepo):ViewModel() {
    val cocktails = MutableStateFlow<CocktailList?>(null)
    val isLoading = MutableStateFlow<Boolean>(false)
    private val error = MutableLiveData<String>()



    fun fetchCocktails(name:String) {
       viewModelScope.launch(Dispatchers.Default) {
           isLoading.value= true
               cocktails.value = cocktailListRepo.getCocktails(name)
           isLoading.value= false

   }
   }

}