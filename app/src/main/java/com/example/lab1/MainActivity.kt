package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            Screen();
        }
    }
}


@Composable
private fun Screen() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
    )
    {
        BackgroundTriangle(Color.Red);

        LazyColumn {
            item {
                Header();
            }
            item {
                Snapper(HeroesData.heros);
            }
        }
    }
}

data class Hero(
    val image: Int,
    val name: String,
    val description: String,

    )


@Composable
fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = "marvel",
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = "Choose your hero",
            style = TextStyle(color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        )
    }
}


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun Snapper(heroes: List<Hero>) {


    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(layoutInfo),
    ) {
        items(items = heroes) { hero ->
            HeroCard(hero)
        }
    }
}

@Composable
fun BackgroundTriangle(backgroundColor: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val trianglePath = Path().apply {
            val height = size.height
            val width = size.width
            moveTo(width, height / 2)
            lineTo(0f, height)
            lineTo(width, height)
        }
        drawPath(trianglePath, color = backgroundColor)
    }
}

@Composable
fun HeroCard(hero: Hero) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10)
            )
    )
    {
        Image(
            painter = painterResource(id = hero.image),
            contentDescription = hero.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(350.dp, 550.dp)
                .clip(RoundedCornerShape(10))
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = hero.name,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
