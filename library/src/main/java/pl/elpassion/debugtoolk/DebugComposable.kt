@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
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
    println("dtk $any")
    Padding(4.dp) {
        Align(Alignment.TopLeft) {
            DrawShape(RectangleShape, +themeColor { background })
            Padding(4.dp) {
                when (any) {
                    null -> Text("null")
                    is List<*> -> ListLog(any)
                    is Number -> Text("number: $any")
                    else -> {
                        Text("somethig else")
                        val properties = any::class.memberProperties
                        AnyLog(properties.map { it.get(any) })
                    }
                }
            }
        }
    }
}

@Composable
private fun ListLog(list: Iterable<*>) = Padding(4.dp) {
    Column(crossAxisAlignment = CrossAxisAlignment.Start) {
        Text("list")
        list.forEach(::AnyLog)
    }
}
