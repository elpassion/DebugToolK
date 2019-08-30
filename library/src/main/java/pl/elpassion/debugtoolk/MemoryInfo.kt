@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import android.text.format.Formatter
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.CoroutineContextAmbient
import androidx.ui.core.Duration
import androidx.ui.core.dp
import androidx.ui.layout.Align
import androidx.ui.layout.Alignment
import androidx.ui.layout.Padding
import androidx.ui.temputils.delay

val runtime = Runtime.getRuntime()

@Composable
fun MemoryInfoContainer(context: Context) {
    val usedMemory = +state { getUsedMemory(context) }
    val freeMemory = +state { getFreeMemory(context) }
    val coroutineContext = +ambient(CoroutineContextAmbient)

    delay(Duration(seconds = 1), coroutineContext) {
        usedMemory.value = getUsedMemory(context)
        freeMemory.value = getFreeMemory(context)
    }

    Section(text = "Memory Info")
    Divider()
    MemoryInfoBody(usedMemory.value, freeMemory.value)
}

@Composable
private fun MemoryInfoBody(usedMemory: String, freeMemory: String) {
    Align(Alignment.TopLeft) {
        Padding(4.dp) {
            BodyText("Used: $usedMemory\nFree: $freeMemory")
        }
    }
}

private fun getUsedMemory(context: Context) =
    (runtime.totalMemory() - runtime.freeMemory()).bytesFormatted(context)

private fun getFreeMemory(context: Context) =
    runtime.freeMemory().bytesFormatted(context)

private fun Long.bytesFormatted(context: Context) =
    Formatter.formatShortFileSize(context, this)
