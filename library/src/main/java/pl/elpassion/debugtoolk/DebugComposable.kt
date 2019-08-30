@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import androidx.compose.Children
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.core.graphics.ColorUtils
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Dialog
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.border.DrawBorder
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.layout.Alignment
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.Padding
import androidx.ui.layout.VerticalScroller
import androidx.ui.material.MaterialTheme
import kotlin.reflect.full.declaredMemberProperties

@Composable
fun DebugComposable(
    context: Context,
    memoryInfo: MemoryInfo,
    log: Any?
) = Dialog(onCloseRequest = {}) {
    MaterialTheme {
        VerticalScroller {
            Padding(8.dp) {
                Column {
                    Header(context)
                    Divider()
                    MemoryInfoContainer(memoryInfo)
                    Divider()
                    LogContainer(log)
                }
            }
        }
    }
}

@Composable
private fun LogContainer(any: Any?) {
    Section(text = "Log")
    Divider()
    AnyLog(any)
}

@Composable
private fun AnyLog(any: Any?, depth: Int = 0) {
    when (any) {
        null -> BodyText("null")
        is List<*> -> CollectionLog(any, depth)
        is Number -> BodyText("number: $any")
        is String -> BodyText("string: $any")
        else -> {
            val properties = any::class.declaredMemberProperties
            AnyLog(properties.map { it.getter.call(any) }, depth)
        }
    }
}

@Composable
private fun NiceBorder(depth: Int, @Children children: @Composable() () -> Unit) =
    Align(Alignment.TopLeft) {
        val lightness = (0.6f + depth * 0.03f) % 1f
        val color = Color(ColorUtils.HSLToColor(floatArrayOf(190f, 0.6f, lightness)))
        val borderColor = Color(ColorUtils.HSLToColor(floatArrayOf(190f, 0.6f, 0.3f)))
        DrawShape(RectangleShape, color)
        DrawBorder(RectangleShape, Border(borderColor, 0.5.dp))
        Padding(left = 4.dp, top = 4.dp, bottom = 4.dp) {
            children()
        }
    }

@Composable
private fun CollectionLog(collection: Collection<*>, depth: Int) = Padding(left = 4.dp) {
    val expand = +state { false }
    NiceBorder(depth) {
        Column(crossAxisAlignment = CrossAxisAlignment.Start) {
            Clickable(onClick = { expand.value = !expand.value }) {
                BodyText("collection (size: ${collection.size})")
                if (expand.value) {
                    collection.forEach { any -> AnyLog(any, depth + 1) }
                }
            }
        }
    }
}
