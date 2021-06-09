package kw.cube.pokemon.network

import kw.cube.pokemon.network.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService{

    @GET("pokemon")
    suspend fun search(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): SearchResponse
}