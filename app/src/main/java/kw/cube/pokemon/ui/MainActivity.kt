package kw.cube.pokemon.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kw.cube.pokemon.ui.list.ListComposable
import kw.cube.pokemon.ui.profile.ProfileComposable
import kw.cube.pokemon.ui.theme.KW3SCPokemonTheme

/**
 * Base activity for our composables.
 */
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KW3SCPokemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Home()
                }
            }
        }
    }
}

/**
 * Base composable & navigation center. Content will change depending on the navHost's
 * destination.
 */
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Home() {
    val navHost = rememberNavController()
    NavHost(navController = navHost, startDestination = "list"){
        composable("list"){ ListComposable(navController = navHost)}
        composable("profile/{id}"){ backStackEntry ->
            ProfileComposable(backStackEntry.arguments?.getString("id"))
            // using ID instead of url because it's difficult to pass the url as a navigation param
        }
    }
}

/**
 * Previews the Home() component.
 */
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KW3SCPokemonTheme {
        Home()
    }
}