package dev.mishkun.trycompose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import pl.elpassion.debugtoolk.DebugComposable
import java.util.*

@Composable
fun SimpleComposable() {
    MaterialTheme {
        Column {
            Text(text = "Hello World!", style = +themeTextStyle { h1 })
            Text(text = "Hello World!", style = +themeTextStyle { h2 })
            Text(text = "Hello World!", style = +themeTextStyle { h3 })
            Text(text = "Hello World!", style = +themeTextStyle { h4 })
            DebugComposable(Date())
        }
    }
}
