package kw.cube.pokemon.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextStat(stat: String, label: String) {
    Column(Modifier.padding(32.dp, 8.dp)) {
        Text(stat, fontSize = 16.sp)
        LabelText(text = label)
    }
}