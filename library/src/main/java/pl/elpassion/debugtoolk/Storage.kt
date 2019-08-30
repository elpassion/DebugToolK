@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.layout.MainAxisAlignment
import androidx.ui.layout.Row
import androidx.ui.material.Button
import java.io.File

@Composable
fun StorageContainer(context: Context) {
    fun getCacheSize(): Long = dirSize(context.cacheDir)
    val cacheSize = +state { getCacheSize().bytesFormatted(context) }

    Section(text = "Storage")
    Divider()
    Row(mainAxisAlignment = MainAxisAlignment.SpaceBetween) {
        BodyText("Cache: ${cacheSize.value}")
        Button("Clear")
    }
}

private fun dirSize(directory: File): Long {
    var length: Long = 0
    for (file in directory.listFiles()) {
        length += if (file.isFile) file.length() else dirSize(file)
    }
    return length
}
