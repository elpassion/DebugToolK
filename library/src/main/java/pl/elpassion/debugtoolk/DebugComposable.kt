@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Dp
import androidx.ui.core.Draw
import androidx.ui.core.Text
import androidx.ui.core.toRect
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import androidx.ui.painting.Paint
import androidx.ui.text.TextStyle

@Composable
fun DebugComposable(any: Any?) {
    MaterialTheme {
        VerticalScroller {
            Column {
                Text(text = "Hello from DebugComposable", style = +themeTextStyle { h3 })
                Column(crossAxisAlignment = CrossAxisAlignment.Start) {
                    (any as? List<*>)?.forEach { AnyLog(it.toString()) }
                }
            }
        }
    }
}

@Composable
private fun AnyLog(text: String) {
    Padding(Dp(4f)) {
        Align(Alignment.TopLeft) {
            Draw { canvas, parentSize ->
                val paint = Paint().apply { this.color = Color.DarkGray }
                canvas.drawRect(parentSize.toRect(), paint)
            }
            Padding(Dp(4f)) {
                Text(
                    text,
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}
