@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.CoroutineContextAmbient
import androidx.ui.core.Duration
import androidx.ui.layout.Align
import androidx.ui.layout.Alignment
import androidx.ui.temputils.delay

@Composable
fun NetworkContainer(context: Context) {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val coroutineContext = +ambient(CoroutineContextAmbient)

    fun isConnected() = cm.activeNetworkInfo?.isConnected ?: false

    val isConnected = +state { isConnected() }

    delay(Duration(seconds = 1), coroutineContext) {
        isConnected.value = isConnected()
    }

    Section(text = "Network")
    Divider()
    NetworkBody(isConnected.value)
}

@Composable
private fun NetworkBody(isConnected: Boolean) {
    Align(Alignment.TopLeft) {
        BodyText("Status: ${if (isConnected) "Connected" else "Disconnected"}")
    }
}
