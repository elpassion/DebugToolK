@file:Suppress("FunctionName")

package pl.elpassion.debugtoolk

import android.content.Context
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.SimpleImage
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.MainAxisAlignment
import androidx.ui.layout.Row
import androidx.ui.material.themeTextStyle
import androidx.ui.painting.imageFromResource

@Composable
fun Header(context: Context) = Row(mainAxisAlignment = MainAxisAlignment.Center) {
    SimpleImage(imageFromResource(context.resources, R.drawable.ic_dev))
    Text(text = "DebugToolK", style = +themeTextStyle { h6 })
}

@Composable
fun Section(text: String) = Text(
    text = text,
    style = +themeTextStyle { subtitle2 }
)

@Composable
fun BodyText(text: String) = Text(
    text = text,
    style = +themeTextStyle { body2 }
)

@Composable
fun SmallText(text: String) = Text(
    text = text,
    style = +themeTextStyle { overline }
)

@Composable
fun Divider() = HeightSpacer(4.dp)
