package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle



@Composable
fun DebugComposable(any: Any?) {
    LogAny(any)
}

@Composable
private fun LogAny(any: Any?) {
    MaterialTheme {
        Text(text = "And the any is: $any", style = +themeTextStyle { h4 })
    }
}
