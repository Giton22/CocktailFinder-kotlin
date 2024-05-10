package com.example.cocktailfinder.cocktailList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.cocktailfinder.data.response.CocktailList
import org.koin.androidx.compose.koinViewModel

@Composable
fun CocktailListScreen(navController: NavController,cocktailListViewModel:CocktailListViewModel = koinViewModel()) {
    var name by remember { mutableStateOf("") }
    val list by cocktailListViewModel.cocktails.collectAsState()
    val loading by cocktailListViewModel.isLoading.collectAsState()
    Surface(
        Modifier.fillMaxSize()
    ){
        Column {
            Spacer(Modifier.padding(30.dp))
            OutlinedTextField(value =name , onValueChange = {name = it}, modifier = Modifier.fillMaxWidth(), label = { Text("Cocktail name")}, singleLine = true, shape = RoundedCornerShape(10.dp))
            OutlinedButton(
                shape = RoundedCornerShape(10.dp),
                onClick = { cocktailListViewModel.fetchCocktails(name) },
                content = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)

            )
            if(loading){
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(androidx.compose.ui.Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                )
            }
            list?.let { CocktailDetailScreen(list = it, clickOnProfile = {id-> navController.navigate("cocktailDetails/$id")}) }
        }


    }

}


@Composable
fun CocktailDetailScreen(list :CocktailList, clickOnProfile:(id:String) ->Unit){
    if (list.drinks != null) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),

    ) {
        items(

            list.drinks,
            key = { it.idDrink!! }
        ) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
                .padding(5.dp)
                .clickable(
                    enabled = true,
                    onClick = {clickOnProfile(item.idDrink.toString()) })
            ) {

            SubcomposeAsyncImage(model = item.strDrinkThumb+"/preview", contentDescription = null, loading = {
                CircularProgressIndicator()
            })
                Text(text = item.strDrink.toString(), color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
            } // Set spot color for the shadow (darker shade)))
        }
    }else{
        Spacer(Modifier.padding(50.dp))
        Text(text = "No cocktails found!", textAlign = TextAlign.Center,modifier= Modifier.fillMaxWidth(), style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))
    }
}