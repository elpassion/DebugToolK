@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.layout.Alignment
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.Padding
import androidx.ui.layout.VerticalScroller
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import androidx.ui.text.TextStyle

@Composable
fun DebugComposable(any: Any?) {
    MaterialTheme {
        VerticalScroller {
            Column {
                Text(text = "Hello from DebugComposable", style = +themeTextStyle { h5 })
                Column(crossAxisAlignment = CrossAxisAlignment.Start) {
                    AnyLog(any)
                }
            }
        }
    }
}

@Composable
private fun AnyLog(any: Any?) {
    Padding(4.dp) {
        Align(Alignment.TopLeft) {
            DrawShape(RectangleShape, Color.DarkGray)
            Padding(4.dp) {
                when (any) {
                    is List<*> -> {
                        Column(crossAxisAlignment = CrossAxisAlignment.Start) {
                            Text(
                                any.toString(),
                                style = TextStyle(color = Color.White),
                                maxLines = 1
                            )
                            any.forEach(::AnyLog)
                        }
                    }
                    else -> Text(
                        any.toString(),
                        style = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}
