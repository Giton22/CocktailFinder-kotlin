package com.example.cocktailfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cocktailfinder.cocktailDetail.CocktailDetailViewModel
import com.example.cocktailfinder.cocktailDetail.CocktailDetailsScreen
import com.example.cocktailfinder.cocktailList.CocktailListScreen
import com.example.cocktailfinder.di.appModule
import com.example.cocktailfinder.ui.theme.CocktailFinderTheme
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CocktailFinderTheme {
                NavHost(navController = navController,startDestination = "cocktailList"){
                    composable("cocktailList"){
                        CocktailListScreen(navController)
                    }
                    composable("cocktailDetails/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                    ){
                        val id = it.arguments?.getString("id")
                        val viewModel: CocktailDetailViewModel = koinViewModel(parameters = { parametersOf(id) })
                        CocktailDetailsScreen(navController,viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CocktailFinderTheme {
        Greeting("Android")
    }
}