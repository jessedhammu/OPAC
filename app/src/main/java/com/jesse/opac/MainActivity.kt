package com.jesse.opac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jesse.opac.ui.theme.OpacTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpacTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "search",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("search") {
                            SearchScreen(
                                onSearch = { title, author, subject, barcode, callNumber, publisher, place, year ->
                                    // TODO: Navigate to results
                                    println("Search clicked with: Title: $title, Author: $author, Subject: $subject, Barcode: $barcode, Call Number: $callNumber, Publisher: $publisher, Place: $place, Year: $year")
                                },
                                onScanBarcode = { /* TODO: Barcode scan */ }
                            )
                        }
                        composable("results") {
                            ResultsScreen(onBookSelected = { /* TODO: Navigate to detail */ })
                        }
                        composable(
                            "bookDetail/{bookId}",
                            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getString("bookId")
                            BookDetailScreen(bookId = bookId)
                        }
                    }
                }
            }
        }
    }
}

// Placeholder Composables for screens
@Composable
fun SearchScreen(
    onSearch: (title: String, author: String, subject: String, barcode: String, callNumber: String, publisher: String, place: String, year: String) -> Unit,
    onScanBarcode: () -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var barcode by remember { mutableStateOf("") }
    var callNumber by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Subject Keywords") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = barcode,
                onValueChange = { barcode = it },
                label = { Text("Barcode") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = onScanBarcode, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Scan Barcode")
            }
        }
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = callNumber,
            onValueChange = { callNumber = it },
            label = { Text("Call Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Publisher") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = place,
            onValueChange = { place = it },
            label = { Text("Publication Place") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Publication Year") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                onSearch(title, author, subject, barcode, callNumber, publisher, place, year)
                Toast.makeText(context, "Search clicked!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
    }
}

@Composable
fun ResultsScreen(onBookSelected: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Results Screen (TODO: Show search results)", color = Color.Red)
    }
}

@Composable
fun BookDetailScreen(bookId: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Book Detail Screen for bookId: $bookId (TODO: Show book details)", color = Color.Red)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpacTheme {
      }
}