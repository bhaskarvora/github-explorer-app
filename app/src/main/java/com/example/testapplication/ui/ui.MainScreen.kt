
package com.example.testapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import coil.compose.rememberImagePainter
import com.example.testapplication.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    initialUsername: String = "",
    viewModel: MainViewModel = viewModel()
) {
    val username by viewModel.username
    val user by viewModel.user
    val error by viewModel.error
    val loading = user == null && error.isEmpty()

    // Launch a side effect to handle initialUsername when it's provided
    LaunchedEffect(initialUsername) {
        if (initialUsername.isNotBlank()) {
            viewModel.onUsernameChange(initialUsername)
            viewModel.searchUser()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GitHub Explorer") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Username input field

            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                label = { Text("GitHub Username") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.searchUser() }) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display error message

            error.takeIf { it.isNotEmpty() }?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Showing skeleton UI while loading

            if (loading) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(20.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.LightGray)
                            .padding(vertical = 4.dp)
                    )
                }
            }

            // Showing user profile information once loaded
            user?.let {
                Image(
                    painter = rememberImagePainter(it.avatarUrl),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .border(2.dp, Color.Gray, CircleShape)
                )

                Text("Username: ${it.login}")
                Text("Name: ${it.name ?: "N/A"}")
                Text("Bio: ${it.bio ?: "N/A"}")

                Spacer(modifier = Modifier.height(10.dp))

                // Navigate to the Followers list
                Row(
                    modifier = Modifier
                        .clickable { navController.navigate("followers/${it.login}") }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = "Followers Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${it.followersCount} followers",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Navigate to the Following list
                Row(
                    modifier = Modifier
                        .clickable { navController.navigate("following/${it.login}") }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = "Following Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${it.followingCount} following",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

