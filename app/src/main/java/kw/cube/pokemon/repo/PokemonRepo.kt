package kw.cube.pokemon.repo

import com.google.gson.GsonBuilder
import kw.cube.pokemon.model.PokemonPreview
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.network.PokemonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepo {

    private val pokemonService: PokemonService

    /**
     * Creates the http client service when initalised. The repo is shared between viewmodels to
     * prevent duplicate code. An more optimal solution might be to dependency inject the service
     * and have it be a singleton to ensure it is only ever created once. https://square.github.io/retrofit/
     */
    init {
        pokemonService = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PokemonService::class.java)
    }

    suspend fun search(offset: Int, limit: Int): List<PokemonPreview> {
        return pokemonService.search(offset = offset, limit = limit).results
    }

    suspend fun getProfile(id: String): PokemonProfile {
        return pokemonService.profile(id = id)
    }

}