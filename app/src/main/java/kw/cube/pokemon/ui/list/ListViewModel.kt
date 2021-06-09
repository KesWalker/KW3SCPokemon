package kw.cube.pokemon.ui.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.repo.PokemonRepo
import kw.cube.pokemon.util.TAG

class ListViewModel: ViewModel(){

    private val repo = PokemonRepo()

    val pokemons: MutableState<List<PokemonPreview>> = mutableStateOf(ArrayList())

    init{
        if(pokemons.value.size == 0){
            searchPokemon()
        }
    }

    fun searchPokemon(){
        viewModelScope.launch {
            val results = repo.search(0,50)
            pokemons.value = results
            Log.d(TAG, "searchPokemon: res: "+results)
        }
    }
}