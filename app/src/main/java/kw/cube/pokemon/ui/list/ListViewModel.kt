package kw.cube.pokemon.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kw.cube.pokemon.repo.PokemonRepo
import kw.cube.pokemon.util.TAG

class ListViewModel: ViewModel(){

    private val repo = PokemonRepo()

    fun searchPokemon(){
        viewModelScope.launch {
            val results = repo.search(0,20)
            Log.d(TAG, "searchPokemon: res: "+results)
        }
    }
}