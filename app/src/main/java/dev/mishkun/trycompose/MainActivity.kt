package dev.mishkun.trycompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.layout.VerticalScroller
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import pl.elpassion.debugtoolk.DebugComposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerticalScroller {
                Column {
                    MaterialTheme {
                        Text(
                            text = "Hello from MainActivity",
                            style = +themeTextStyle { h4 })
                    }
                    val logs = List(10) { List(3) { List(2) { "Log $it" } } }
                    DebugComposable(logs)
                }
            }
        }
    }
}
