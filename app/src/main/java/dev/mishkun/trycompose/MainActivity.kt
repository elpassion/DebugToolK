package dev.mishkun.trycompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import pl.elpassion.debugtoolk.DebugComposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val logs = List(30) { "Log $it" }
            DebugComposable(logs)
        }
    }
}
