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
import kotlin.reflect.full.declaredMemberProperties

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
    Align(Alignment.TopLeft) {
        DrawShape(RectangleShape, +themeColor { background })
        when (any) {
            null -> TextLog("null")
            is List<*> -> CollectionLog(any)
            is Number -> TextLog("number: $any")
            is String -> TextLog("string: $any")
            else -> {
                val properties = any::class.declaredMemberProperties
                AnyLog(properties.map { it.getter.call(any) })
            }
        }
    }
}

@Composable
private fun CollectionLog(collection: Collection<*>) = Padding(left = 4.dp) {
    Column(crossAxisAlignment = CrossAxisAlignment.Start) {
        TextLog("collection (size: ${collection.size})")
        collection.forEach(::AnyLog)
    }
}

@Composable
private fun TextLog(text: String) = Text(
    text = text,
    style = +themeTextStyle { body2 }
)
