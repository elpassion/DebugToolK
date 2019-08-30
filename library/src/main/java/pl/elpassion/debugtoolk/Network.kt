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

    fun getStatus() = cm.activeNetworkInfo?.detailedState?.name ?: "DISCONNECTED"
    fun getType() = cm.activeNetworkInfo?.typeName.orEmpty()

    val status = +state { getStatus() }
    val type = +state { getType() }

    delay(Duration(seconds = 1), coroutineContext) {
        status.value = getStatus()
        type.value = getType()
    }

    Section(text = "Network")
    Divider()
    NetworkBody(status.value, type.value)
}

@Composable
private fun NetworkBody(status: String, info: String) {
    Align(Alignment.TopLeft) {
        BodyText("Status: $status\n${if (info.isNotEmpty()) "Type: $info" else ""}")
    }
}
