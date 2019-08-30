@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import android.text.format.Formatter
import androidx.compose.Composable

class DebugToolK(private val context: Context) {

    private val runtime = Runtime.getRuntime()
    private val memoryInfo
        get() = MemoryInfo(
            used = (runtime.totalMemory() - runtime.freeMemory()).formatted(),
            free = runtime.freeMemory().formatted()
        )

    @Composable
    fun Dialog(any: Any?) {
        DebugComposable(context, memoryInfo, any)
    }

    private fun Long.formatted() = Formatter.formatShortFileSize(context, this)
}


data class MemoryInfo(val used: String, val free: String)
