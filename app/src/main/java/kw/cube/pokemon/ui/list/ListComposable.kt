package kw.cube.pokemon.ui.list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kw.cube.pokemon.ui.Home
import kw.cube.pokemon.ui.theme.KW3SCPokemonTheme

@Composable
fun ListComposable(navController: NavController){

    val viewModel: ListViewModel = viewModel()
    viewModel.searchPokemon()

    Text("hello")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KW3SCPokemonTheme {
        ListComposable(rememberNavController())
    }
}