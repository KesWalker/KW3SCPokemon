package kw.cube.pokemon.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.glide.rememberGlidePainter
import kw.cube.pokemon.R
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.ui.components.ErrorMessage
import kw.cube.pokemon.ui.components.LoadingSpinner
import kw.cube.pokemon.ui.theme.KW3SCPokemonTheme
import kw.cube.pokemon.util.imgHost

/**
 * Interacts with ListViewModel to display a list of pokemons. Uses NavController to navigate
 * to speciifc pokemon profiles. Monitors state of pokemon list & error boolean within viewModel.
 * Will re-create it's self if any state changes -> https://developer.android.com/jetpack/compose/state
 *
 * viewModel is in the constructor but it should only be created once and should remain
 * available as long as the base activity is alive. See https://developer.android.com/jetpack/compose/libraries#viewmodel
 */
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ListComposable(navController: NavController, viewModel: ListViewModel = viewModel()) {
    val pokemons = viewModel.pokemons.value
    val error = viewModel.error.value

    if (pokemons.size == 0 && !error) {
        LoadingSpinner()
    } else if (error) {
        ErrorMessage()
    } else {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
            itemsIndexed(pokemons) { index, item ->
                PokemonNameImage(navController, item)
            }
        }
    }
}

/**
 * Displays a single image using the image url in the pokemonPreview object & a single peice
 * of text with the pokemons name. Uses a material theme Card widget. Image is loaded from
 * the internet with Glide, see more here -> https://google.github.io/accompanist/glide/
 */
@ExperimentalMaterialApi
@Composable
fun PokemonNameImage(navController: NavController, pokemon: PokemonPreview) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        onClick = { navController.navigate("profile/" + pokemon.getId()) }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberGlidePainter(
                    pokemon.getImgUrl(),
                    fadeIn = true,
                    previewPlaceholder = R.drawable.unknown
                ),
                contentDescription = pokemon.name,
                Modifier
                    .height(96.dp)
                    .width(96.dp)
            )
            Text(pokemon.name, Modifier.padding(4.dp))
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KW3SCPokemonTheme {
        ListComposable(rememberNavController())
    }
}