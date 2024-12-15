package com.example.lemonade

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
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

/* *
 * Interface graphique de l'application
 * */
@Composable
fun LemonadeApp() {
    // Suivi de l'état actuel de l'application (0: sélectionner, 1: presser, 2: boire, 3: redémarrer)
    var currentIndex by remember { mutableIntStateOf(0) }
    // Nombre de pressions nécessaires pour presser le citron
    var squeezeTapCount by remember { mutableIntStateOf(0) }
    // Indicateur si le citron est en cours de pressage
    var isSqueezed by remember { mutableStateOf(false) }

    // Texte et image à afficher en fonction de l'état actuel de l'application
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
    // Description de l'image en fonction de l'état actuel de l'application
    val imageDescription = when (currentIndex) {
        0 -> R.string.lemon_tree
        1 -> R.string.lemon
        2 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    // Contexte pour charger l'animation depuis les ressources
    val context = LocalContext.current
    val animation = remember { AnimationUtils.loadAnimation(context, R.anim.shake_animation) }

    LemonadeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // En-tête avec le nom de l'application
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFF063))
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.app_name), color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }

                // Conteneur principal
                Column(
                    modifier = Modifier.weight(1f), // Alloue de la place au contenu
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Zone cliquable pour changer d'état
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(bottom = 35.dp)
                            .background(Color(0xFF93E5AB), shape = RoundedCornerShape(45.dp))
                            .clickable {
                                when (currentIndex) {
                                    0 -> { // Sélectionner le citron
                                        squeezeTapCount = (2..4).random()
                                        currentIndex = 1
                                    }

                                    1 -> { // Presser le citron
                                        isSqueezed = true
                                        currentIndex =
                                            if (squeezeTapCount-- == 1) {
                                                isSqueezed = false
                                                2
                                            } else currentIndex
                                    }

                                    2 -> currentIndex = 3 // Boire le jus
                                    else -> currentIndex = 0 // Réinitialiser
                                }
                            },
                    ) {
                        if (!isSqueezed) { // Afficher l'image de base
                            Image(
                                painter = painterResource(imageResource),
                                contentDescription = imageDescription.toString(),
                                modifier = Modifier
                                    .padding(30.dp, 40.dp)
                                    .size(200.dp)
                            )
                        } else { // Afficher l'image animée
                            AndroidView(
                                modifier = Modifier
                                    .padding(30.dp, 40.dp)
                                    .size(200.dp),
                                factory = { ctx ->
                                    ImageView(ctx).apply {
                                        setImageResource(imageResource)
                                        startAnimation(animation)
                                    }
                                }
                            )
                        }
                    }

                    // Texte correspondant à l'état actuel
                    Text(
                        stringResource(id = imageText),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// Prévisualisation
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeApp()
}
