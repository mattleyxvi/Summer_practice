package com.example.practice_marvel_2024


import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter

public var HeroesPool = listOf(
    Hero("IronMan",
        "https://toplogos.ru/images/logo-iron-man.jpg",
        "https://809620.selcdn.ru/wallpaperio-net/wallpapers/full/cbe59912c2c7b5764b81f9978c6014b2.jpg",
        "Рекорды устанавливают для того, чтобы их можно было побить!"),

    Hero("SpiderMan",
        "https://i.pinimg.com/736x/39/dc/84/39dc84dc1531d54365ce0faf105e6ffa--spiderman-hoodie-spiderman-logo.jpg",
        "https://809620.selcdn.ru/wallpaperio-net/wallpapers/full/96fb2c30a390c18ed981aae71784ff42.jpg",
        "У всех есть тайны. Что-то скрываем мы, что-то скрывают от нас."),

    Hero("Hulk",
        "https://w0.peakpx.com/wallpaper/964/303/HD-wallpaper-logo-hulk-avengers-thumbnail.jpg",
        "https://809620.selcdn.ru/wallpaperio-net/wallpapers/full/c491de6db0272c259224e164f8b7de84.jpg",
        "Когда я чего-то не понимаю, захожу в тупик — во мне Халк просыпается, и я бешусь."))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }


    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun HeroesList() {
        val LazyState = rememberLazyListState()
        val snapBehaviour = rememberSnapFlingBehavior(lazyListState = LazyState)

        LazyRow(state = LazyState, flingBehavior = snapBehaviour) {
            items(HeroesPool) { hero ->
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    var isClicked by mutableStateOf(false)
                    Box(Modifier.size(650.dp),
                        contentAlignment = Alignment.BottomCenter) {
                        IconButton(onClick = {isClicked = true},
                            Modifier.fillMaxSize()) {
                            if (isClicked) {
                                setContent {
                                    HeroCard(hero = hero)
                                }

                                isClicked = false }
                        }

                        Image(
                            painter = rememberAsyncImagePainter(
                                model = hero.avatar, imageLoader = ImageLoader(
                                    LocalContext.current
                                )
                            ),

                            contentDescription = "avatar",
                            modifier = Modifier.size(700.dp), alignment = Alignment.Center
                        )
                    }

                    Text(
                        text = hero.name,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 30.sp, color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "MARVEL_logotype"
            )

            Text(
                text = "CHOOSE YOUR HERO",
                color = Color.White,
                fontSize = 45.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            HeroesList()
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun HeroCard(hero: Hero) {
        var isClicked by mutableStateOf(false)
        Image(
            painter = rememberAsyncImagePainter(
                model = hero.logo, imageLoader = ImageLoader(
                    LocalContext.current
                )
            ),

            contentDescription = "logotype",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(), alignment = Alignment.Center)

        IconButton(onClick = { isClicked = true }, modifier = Modifier.size(70.dp)) {
            Icon(Icons.Default.Close, contentDescription = "Return", tint = Color.Gray)
            if (isClicked) {
                setContent {
                    MainScreen()
                }

                isClicked = false
            }
        }

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            Column {
                Text(
                    text = hero.name, fontFamily = FontFamily.Default,
                    fontSize = 35.sp, color = Color.White, fontWeight = FontWeight.Black
                )

                Text(
                    text = hero.info,
                    fontFamily = FontFamily.Default,
                    fontSize = 30.sp, color = Color.White,
                    fontWeight = FontWeight.Black
                )
            }

        }
    }
}
