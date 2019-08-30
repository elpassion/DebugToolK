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
import androidx.ui.temputils.delay

class DebugToolK(private val context: Context) {

    private val runtime = Runtime.getRuntime()

    @Composable
    fun Dialog(any: Any?) {
        val usedMemory = +state { getUsedMemory() }
        val freeMemory = +state { getFreeMemory() }
        val coroutineContext = +ambient(CoroutineContextAmbient)
        fun updateMemoryInfo() {
            usedMemory.value = getUsedMemory()
            freeMemory.value = getFreeMemory()
        }
        delay(Duration(seconds = 1), coroutineContext) {
            updateMemoryInfo()
        }
        DebugComposable(
            context,
            MemoryInfo(usedMemory.value, freeMemory.value),
            any
        )
    }

    private fun getUsedMemory() = (runtime.totalMemory() - runtime.freeMemory()).bytesFormatted()

    private fun getFreeMemory() = runtime.freeMemory().bytesFormatted()

    private fun Long.bytesFormatted() = Formatter.formatShortFileSize(context, this)
}

data class MemoryInfo(val used: String, val free: String)
