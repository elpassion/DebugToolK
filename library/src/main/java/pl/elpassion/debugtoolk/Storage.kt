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
    val cacheDir = context.cacheDir
    fun getCacheSize(): Long = dirSize(cacheDir)
    val cacheSize = +state { getCacheSize().bytesFormatted(context) }

    fun clearCache() {
        if (cacheDir.exists()) {
            cacheDir.list().forEach { child ->
                if (child != "lib") {
                    deleteDir(File(cacheDir, child))
                }
            }
        }
        cacheSize.value = getCacheSize().bytesFormatted(context)
    }

    Section(text = "Storage")
    Divider()
    Row(mainAxisAlignment = MainAxisAlignment.SpaceBetween) {
        BodyText("Cache: ${cacheSize.value}")
        Button("Clear", onClick = { clearCache() })
    }
}

private fun dirSize(directory: File): Long {
    var length: Long = 0
    for (file in directory.listFiles()) {
        length += if (file.isFile) file.length() else dirSize(file)
    }
    return length
}

private fun deleteDir(dir: File?): Boolean {
    if (dir != null && dir.isDirectory) {
        val children = dir.list()
        for (i in children!!.indices) {
            val success = deleteDir(File(dir, children[i]))
            if (!success) {
                return false
            }
        }
    }
    return dir!!.delete()
}
