@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.layout.Align
import androidx.ui.layout.Alignment
import androidx.ui.layout.Padding

@Composable
fun MemoryInfoContainer(memoryInfo: MemoryInfo) {
    Section(text = "Memory Info")
    Divider()
    Align(Alignment.TopLeft) {
        Padding(4.dp) {
            BodyText("Used: ${memoryInfo.used}\nFree: ${memoryInfo.free}")
        }
    }
}
