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
import androidx.ui.material.MaterialColors
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeColor
import androidx.ui.material.themeTextStyle
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

@Composable
fun DebugComposable(any: Any?) {
    MaterialTheme(
        colors = MaterialColors(background = Color(0xFFCFD8DC.toInt()))
    ) {
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
            DrawShape(RectangleShape, +themeColor { background })
            Padding(4.dp) {
                when (any) {
                    null -> Text("null")
                    is List<*> -> Column(crossAxisAlignment = CrossAxisAlignment.Start) {
                        Text(
                            any.toString(),
                            style = +themeTextStyle { body2 },
                            maxLines = 2
                        )
                        any.forEach(::AnyLog)
                    }
                    else -> {
                        val properties = any::class.memberProperties as List<KProperty1<Any, *>>
                        properties.map { it.get(any) }.filterIsInstance<List<*>>().forEach(::AnyLog)
                    }
                }
            }
        }
    }
}
