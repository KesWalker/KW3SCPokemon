package kw.cube.pokemon.ui.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileComposable(url: String?){
Text(url ?: "not found")
}