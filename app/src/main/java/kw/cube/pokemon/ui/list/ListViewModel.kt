package kw.cube.pokemon.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.repo.PokemonRepo
import java.lang.Exception

class ListViewModel : ViewModel() {

    private val repo = PokemonRepo()

    val pokemons: MutableState<List<PokemonPreview>> = mutableStateOf(ArrayList())
    val error: MutableState<Boolean> = mutableStateOf(false)

    init {
        if (pokemons.value.size == 0) {
            searchPokemon()
        }
    }

    /**
     * Retrieves all pokemons with thier names & urls. We know that there are a total of 1118
     * pokemons to get so we get them all at once. A more optimal solution would be to use
     * pagination to retrieve 50 at a time, this would reduce our bandwidth usage.
     */
    fun searchPokemon() {
        viewModelScope.launch {
            try{
                error.value = false
                val results = repo.search(0, 1118)
                pokemons.value = results
            }catch (e: Exception){
                error.value = true
            }
        }
    }
}