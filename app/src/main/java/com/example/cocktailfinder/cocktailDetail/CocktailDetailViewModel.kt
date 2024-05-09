package com.example.cocktailfinder.cocktailDetail

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailfinder.cocktailList.CocktailListRepo
import com.example.cocktailfinder.data.response.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class CocktailDetailViewModel(private val id: String, private val repository: CocktailListRepo): ViewModel() {
    val _cocktailDetail = MutableStateFlow<Drink?>(null)

    init {
        getCocktailDetail(id)
    }

    private fun getCocktailDetail(id: String) {
        viewModelScope.launch(Dispatchers.Default) {
            var res = repository.getCocktail(id)
            _cocktailDetail.value = res.drinks?.get(0)
        }
    }

}