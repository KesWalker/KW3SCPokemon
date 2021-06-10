package kw.cube.pokemon.ui.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.repo.PokemonRepo
import kw.cube.pokemon.util.TAG
import java.lang.Exception

class ProfileViewModel : ViewModel() {

    private val repo = PokemonRepo()

    val profile: MutableState<PokemonProfile?> = mutableStateOf(null)
    val error: MutableState<Boolean> = mutableStateOf(false)

    /**
     * Uses the ID of the pokemon to retrieve more details. If user's device is offline, an exception
     * will be thrown and caught, the 'error' state variable will be changed and the UI will
     * reflect this by showing an error message.
     */
    fun getProfile(id: String) {
        viewModelScope.launch {
            try{
                error.value = false
                val result = repo.getProfile(id)
                profile.value = result
                Log.d(TAG, "getProfile: res: " + result)
            }catch (e : Exception){
                error.value = true
            }
        }
    }
}