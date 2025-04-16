package com.example.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab04.ui.theme.Lab04Theme
import androidx.compose.material3.ExperimentalMaterial3Api

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04Theme {
                MainScreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Aplicación") },
                colors = centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MainContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "¡Bienvenido!", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Selecciona la dificultad:", style = MaterialTheme.typography.bodyLarge)

        DifficultySelector()

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Personajes:", style = MaterialTheme.typography.bodyLarge)

        CharacterImageCarousel()
    }
}

@Composable
fun DifficultySelector() {
    val difficulties = listOf("Fácil", "Medio", "Difícil")
    var selectedIndex by remember { mutableStateOf(-1) }

    Column {
        difficulties.forEachIndexed { index, difficulty ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = selectedIndex == index,
                    onCheckedChange = {
                        selectedIndex = if (selectedIndex == index) -1 else index
                    },
                    colors = CheckboxDefaults.colors()
                )
                Text(text = difficulty)
            }
        }
    }
}

@Composable
fun CharacterImageCarousel() {
    val imageList = listOf(
        R.drawable.p065,
        R.drawable.p066,
        R.drawable.p074,
        R.drawable.p088,
        R.drawable.p116
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(imageList.size) { index ->
            CharacterCard(imageRes = imageList[index])
        }
    }
}

@Composable
fun CharacterCard(imageRes: Int) {
    Card(
        modifier = Modifier.size(100.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Imagen del personaje",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab04Theme {
        MainScreen()
    }
}
