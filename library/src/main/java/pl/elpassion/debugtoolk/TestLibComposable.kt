@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.core.Draw
import androidx.ui.core.Text
import androidx.ui.core.toRect
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.layout.Alignment
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.Padding
import androidx.ui.layout.VerticalScroller
import androidx.ui.painting.Paint
import androidx.ui.text.TextStyle

@Composable
fun DebugComposable(any: Any?) {
    VerticalScroller {
        Column(crossAxisAlignment = CrossAxisAlignment.Start) {
            (any as? List<*>)?.forEach { AnyLog(it.toString()) }
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
