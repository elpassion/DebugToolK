@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import androidx.compose.Composable
import androidx.ui.layout.MainAxisAlignment
import androidx.ui.layout.Row
import androidx.ui.material.Button

@Composable
fun StorageContainer() {
    Section(text = "Storage")
    Divider()
    Row(mainAxisAlignment = MainAxisAlignment.SpaceBetween) {
        BodyText("Cache: 0 MB")
        Button("Clear")
    }
}
