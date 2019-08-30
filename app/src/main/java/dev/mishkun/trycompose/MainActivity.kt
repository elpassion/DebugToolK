@file:Suppress("FunctionName")

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
import pl.elpassion.debugtoolk.DebugToolK
import kotlin.random.Random

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
                    DebugToolK(this, getLog())
                }
            }
        }
    }

}

private fun getLog(): List<A> {
    val c = List(3) { C(RandomString(), Random.nextInt(), Random.nextFloat()) }
    val b = List(2) { B(RandomString(), c) }
    return List(Random.nextInt(2, 5)) { A(RandomString(), RandomString(), b) }
}

private fun RandomString(): String {
    val leftLimit = 97 // letter 'a'
    val rightLimit = 122 // letter 'z'
    val targetStringLength = Random.nextInt(3, 15)
    val buffer = StringBuilder(targetStringLength)
    for (i in 0 until targetStringLength) {
        val randomLimitedInt =
            leftLimit + (Random.nextFloat() * (rightLimit - leftLimit + 1)).toInt()
        buffer.append(randomLimitedInt.toChar())
    }
    return buffer.toString()
}

data class A(val a: String, val b: String, val c: List<B>)

data class B(val a: String, val b: List<C>)

data class C(val a: String, val b: Int, val c: Float)
