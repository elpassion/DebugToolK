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
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.reflect.full.declaredMemberProperties

@Composable
fun DebugToolK(
    context: Context,
    log: Any?,
    onCloseRequest: () -> Unit
) = Dialog(onCloseRequest = onCloseRequest) {
    MaterialTheme {
        VerticalScroller {
            Padding(8.dp) {
                Column {
                    Header(context)
                    Divider()
                    MemoryInfoContainer(context)
                    Divider()
                    StorageContainer()
                    Divider()
                    NetworkContainer(context)
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
    Section(text = "Log2")
    Divider()
    Log2()
}

@Composable
private fun Log2() {
    for (line in getLogs()) SmallText(line)
}

private fun getLogs(): List<String> {
    val process = runtime.exec("logcat -d")
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    return reader.readLines().takeLast(10)
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
