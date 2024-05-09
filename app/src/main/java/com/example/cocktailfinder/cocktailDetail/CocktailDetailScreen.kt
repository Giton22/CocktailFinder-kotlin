package com.example.cocktailfinder.cocktailDetail

import android.widget.VideoView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun CocktailDetailsScreen(navController: NavController,cocktailDetailViewModel: CocktailDetailViewModel=koinViewModel()){
    val cocktail = cocktailDetailViewModel._cocktailDetail.collectAsState()


    Surface(
        Modifier.fillMaxSize()
    ) {
        if(cocktail.value!=null){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(modifier = Modifier.padding(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back icon
                IconButton(onClick = navController::popBackStack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                // Title
            }
            Text(text = cocktail.value!!.strDrink.toString(), style = TextStyle(fontSize = MaterialTheme.typography.headlineLarge.fontSize, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.padding(20.dp))
            SubcomposeAsyncImage(model = cocktail.value!!.strDrinkThumb, contentDescription = null, loading = {
                CircularProgressIndicator()
            })
            Spacer(modifier = Modifier.padding(20.dp))
            Text(text = cocktail.value!!.strInstructions.toString(), textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.padding(20.dp))
            Text(text = "Ingredients",style = TextStyle(fontSize = MaterialTheme.typography.headlineSmall.fontSize, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.padding(5.dp))
            val ingredientProperties = cocktail.value!!::class.members
                .filter { it.name.startsWith("strIngredient") }
            val measureProperties = cocktail.value!!::class.members
                .filter { it.name.startsWith("strMeasure") }

            // Iterate over the ingredient and measure properties and display them
            for (i in 1..10) {
                val ingredientProperty = ingredientProperties.find { it.name == "strIngredient$i" }
                val measureProperty = measureProperties.find { it.name == "strMeasure$i" }

                if (ingredientProperty != null && measureProperty != null) {
                    val ingredientValue = ingredientProperty.call(cocktail.value!!) as? String
                    val measureValue = measureProperty.call(cocktail.value!!) as? String

                    if (ingredientValue != null && measureValue != null) {
                        Text(text = "$ingredientValue - $measureValue")
                    }
                }
            }

            Spacer(modifier = Modifier.padding(300.dp))
            }
        }
    }

}