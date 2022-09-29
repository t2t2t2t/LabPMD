package com.example.firstlabandroid

import android.nfc.cardemulation.CardEmulation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import org.intellij.lang.annotations.JdkConstants
import kotlin.math.sqrt
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            Screen();
        }
    }
}


@Composable
private fun   Screen() {
    Box(modifier = Modifier
        .background(Color.DarkGray)
        .fillMaxSize())
    {
        RoundedTriangle();

        LazyColumn {
            item {
                Header();
            }
            item {
                Snapper()
            }
        }
    }
}
@Composable
fun BoxCard(
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .size(width = 400.dp, height = 100.dp)
                ){
        Text(text = "Image hero", style = TextStyle(color = Color.White, fontSize = 20.sp))
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun Snapper(
) {
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState),
    ) {
        item {
            Image()
        }

        item {
            Image()
        }
        item {
            Image()
        }
        item {
            Image()
        }
        item {
            Image()
        }
    }
}

@Composable
fun RoundedTriangle() {
    Canvas(modifier = Modifier
        .fillMaxSize()
        //.background(Color.Green)
    ) {
        val trianglePath = Path().apply {
            val height = size.height
            val width = size.width
            moveTo(width, height / 2)
            lineTo(0f, height)
            lineTo(width, height)
        }
        drawPath(trianglePath, color = Color.Red)
    }
}

@Composable
fun Image() {
    Box(modifier = Modifier
        .fillMaxSize()
        //.background(Color.Green).=
        .padding(30.dp))
    {

        Image(
            painter = painterResource(id = R.drawable.deadpool),
            contentDescription = "deadpool",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(350.dp,550.dp)
                .clip(RoundedCornerShape(10))

        )
    }

}

@Composable
fun Header() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            //.background(Color.Red)
            .fillMaxSize().padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = "marvel",
            contentScale = ContentScale.FillWidth,
            //modifier = Modifier.size(120.dp),
        )
        Text(text = "Choose your hero", style = TextStyle(color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold))
    }
}

