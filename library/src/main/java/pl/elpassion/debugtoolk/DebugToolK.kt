@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import android.text.format.Formatter
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus

class DebugToolK(private val context: Context) {

    private val runtime = Runtime.getRuntime()

    @Composable
    fun Dialog(any: Any?) {
        val usedMemory = +state { getUsedMemory() }
        val freeMemory = +state { getFreeMemory() }
        fun updateMemoryInfo() {
            usedMemory.value = getUsedMemory()
            freeMemory.value = getFreeMemory()
        }
        DebugComposable(
            context,
            MemoryInfo(usedMemory.value, freeMemory.value),
            any,
            ::updateMemoryInfo
        )
    }

    private fun getUsedMemory() = (runtime.totalMemory() - runtime.freeMemory()).bytesFormatted()

    private fun getFreeMemory() = runtime.freeMemory().bytesFormatted()

    private fun Long.bytesFormatted() = Formatter.formatShortFileSize(context, this)
}

data class MemoryInfo(val used: String, val free: String)
