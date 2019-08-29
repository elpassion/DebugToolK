package dev.mishkun.trycompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import pl.elpassion.debugtoolk.DebugComposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                MaterialTheme { Text(text = "Hello from MainActivity", style = +themeTextStyle { h3 }) }
                val logs = List(30) { "Log $it" }
                DebugComposable(logs)
            }
        }
    }
}
