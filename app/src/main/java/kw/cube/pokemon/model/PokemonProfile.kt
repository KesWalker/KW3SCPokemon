package kw.cube.pokemon.model

import kw.cube.pokemon.util.imgHost

/**
 * Contains all the attributes of an individual pokemon. Although slightly less than the total
 * available attributes, I removed a number of ones I thought were unnecessary.
 * I used this plugin to help convert JSON strings into Kotlin classes:
 * https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */
data class PokemonProfile(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
){
    fun getImgUrl(): String {
        return imgHost + id + ".png";
    }
}