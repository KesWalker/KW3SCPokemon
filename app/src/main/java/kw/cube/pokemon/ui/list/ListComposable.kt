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
import kw.cube.pokemon.ui.theme.KW3SCPokemonTheme
import kw.cube.pokemon.util.imgHost

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ListComposable(navController: NavController) {
    val viewModel: ListViewModel = viewModel()
    val pokemons = viewModel.pokemons.value
    if (pokemons.size == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
            itemsIndexed(pokemons) { index, item ->
                Card(
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    elevation = 4.dp,
                    onClick = { navController.navigate("profile/" + item.getId()) }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = rememberGlidePainter(item.getImgUrl(),fadeIn = true,previewPlaceholder = R.drawable.unknown),
                            contentDescription = item.name,
                            Modifier.height(96.dp).width(96.dp)
                        )
                        Text(item.name)
                    }
                }
            }
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