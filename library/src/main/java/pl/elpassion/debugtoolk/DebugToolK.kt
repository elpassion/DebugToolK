@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import android.text.format.Formatter
import androidx.compose.Composable
import androidx.compose.Model

class DebugToolK(private val context: Context) {

    private val runtime = Runtime.getRuntime()
    private val memoryInfo = MemoryInfo()

    init {
//        GlobalScope.launch(Dispatchers.Main) {
//            while (isActive) {
        memoryInfo.used = (runtime.totalMemory() - runtime.freeMemory()).bytesFormatted()
        memoryInfo.free = runtime.freeMemory().bytesFormatted()
//            }
//        }
    }

    @Composable
    fun Dialog(any: Any?) {
        DebugComposable(context, memoryInfo, any)
    }

    private fun Long.bytesFormatted() = Formatter.formatShortFileSize(context, this)
}

@Model
class MemoryInfo {
    var used: String = ""
    var free: String = ""
}
