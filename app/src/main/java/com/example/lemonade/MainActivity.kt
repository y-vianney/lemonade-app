package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentIndex by remember { mutableIntStateOf(0) }
    var squeezeTapCount by remember { mutableIntStateOf(0) }

    val imageText = when (currentIndex) {
        0 -> R.string.select_lemon
        1 -> R.string.squeeze_lemon
        2 -> R.string.drink_lemonade
        else -> R.string.restart_process
    }

    val imageResource = when (currentIndex) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    LemonadeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFEB3B))
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.app_name), color = Color.Black, fontSize = 24.sp)
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(300.dp)
                            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
                            .clickable {
                                when (currentIndex) {
                                    0 -> {
                                        squeezeTapCount = (2..4).random()
                                        print("Random number: $squeezeTapCount")
                                        currentIndex = 1
                                    }

                                    1 -> {
                                        currentIndex =
                                            if (squeezeTapCount-- == 1) 2 else currentIndex
                                    }

                                    2 -> currentIndex = 3
                                    else -> currentIndex = 0
                                }
                            },
                    ) {
                        Image(
                            painter = painterResource(imageResource),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        stringResource(id = imageText),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeApp()
}