package kw.cube.pokemon.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState
import kw.cube.pokemon.R
import kw.cube.pokemon.model.PokemonProfile
import kw.cube.pokemon.model.Sprites
import kw.cube.pokemon.model.Stat
import kw.cube.pokemon.ui.components.ErrorMessage
import kw.cube.pokemon.ui.components.LoadingSpinner
import kw.cube.pokemon.ui.components.TextStat
import kw.cube.pokemon.ui.list.ProfileViewModel

@ExperimentalFoundationApi
@Composable
fun ProfileComposable(id: String?) {
    val viewModel: ProfileViewModel = viewModel()
    if (id != null) {
        viewModel.getProfile(id)
        val profile = viewModel.profile.value
        val error = viewModel.error.value
        if (profile == null && !error) {
            LoadingSpinner()
        } else if (error) {
            ErrorMessage()
        } else if (profile != null) {
            ProfileMain(profile = profile)
        }
    } else {
        ErrorMessage()
    }
}

@ExperimentalFoundationApi
@Composable
fun ProfileMain(profile: PokemonProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(0.dp, 8.dp, 0.dp, 8.dp)
        ) {
            ProfilePic(profile.getImgUrl(), profile.name)
            Column(Modifier.padding(16.dp)) {
                ProfileName(profile.name)
                Stats(profile.stats)
            }
        }
        Sprites(sprites = profile.sprites)
        TextStat(profile.id.toString(), stringResource(R.string.pokemon_num))
        TextStat(profile.species.name, stringResource(R.string.species))
        TextStat(profile.types.joinToString { it.type.name }, stringResource(R.string.types))
        TextStat(profile.weight.toString(), stringResource(R.string.weight))
        TextStat(profile.forms.joinToString { it.name }, stringResource(R.string.forms))
    }
}

@Composable
fun ProfilePic(url: String, name: String) {
    Image(
        painter = rememberGlidePainter(url, fadeIn = true, previewPlaceholder = R.drawable.unknown),
        contentDescription = name,
        modifier = Modifier
            .height(128.dp)
            .width(128.dp)
    )
}

@Composable
fun ProfileName(name: String) {
    Text(
        name,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
    )
}

@Composable
fun StatBox(stat: Stat) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp, 0.dp)) {
        Text(
            "${stat.base_stat}",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            stat.stat.name,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun Stats(stats: List<Stat>) {
    Row {
        Column {
            for (stat in stats.subList(0, 3)) {
                StatBox(stat = stat)
            }
        }
        Column {
            for (stat in stats.subList(3, 6)) {
                StatBox(stat = stat)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Sprites(sprites: Sprites) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            SpriteBox(url = sprites.front_default)
            SpriteBox(url = sprites.back_default)
            SpriteBox(url = sprites.front_shiny)
            SpriteBox(url = sprites.back_shiny)
        }
        Text(stringResource(R.string.def_shiny), fontSize = 14.sp, color = Color.Gray)
        if (sprites.front_female != null && sprites.back_female != null && sprites.front_shiny_female != null && sprites.back_shiny_female != null) {
            Row {
                SpriteBox(url = sprites.front_female)
                SpriteBox(url = sprites.back_female)
                SpriteBox(url = sprites.front_shiny_female)
                SpriteBox(url = sprites.back_shiny_female)
            }
            Text(stringResource(R.string.female_shiny), fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun RowScope.SpriteBox(url: String) {
    val painter = rememberGlidePainter(url, fadeIn = true, previewPlaceholder = R.drawable.unknown)
    Image(
        painter = painter,
        contentDescription = stringResource(R.string.sprite),
        modifier = Modifier.weight(1f)
    )
    when (painter.loadState) {
        is ImageLoadState.Loading -> {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }
    }
}