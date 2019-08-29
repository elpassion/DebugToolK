package pl.elpassion.debugtoolk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import java.util.*

class DebugActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DebugComposable(Date()) }
    }
}
