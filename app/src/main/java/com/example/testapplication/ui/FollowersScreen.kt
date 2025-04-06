
package com.example.testapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.testapplication.data.RetrofitInstance
import com.example.testapplication.model.Follower
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowersScreen(navController: NavController, username: String) {
    // List of users who follow the given user
    var followers by remember { mutableStateOf<List<Follower>>(emptyList()) }

    //  Holds if any error message from the API call
    var error by remember { mutableStateOf("") }

    // Scope to launch coroutines for data fetching
    var loading by remember { mutableStateOf(true) }

    // State for Swipe-to-Refresh UI
    val scope = rememberCoroutineScope()
    // State for Swipe-to-Refresh UI
    val refreshState = rememberSwipeRefreshState(isRefreshing = loading)

    //Loads the list of followers from GitHub API
    fun loadFollowers() {
        scope.launch {
            try {
                loading = true
                delay(3000)
                followers = RetrofitInstance.api.getFollowers(username)
                error = ""
            } catch (e: Exception) {
                error = "Failed to load followers"
            } finally {
                loading = false
            }
        }
    }

    LaunchedEffect(username) {
        loadFollowers()
    }

    Scaffold(
        topBar = {

            // App bar with back navigation
            TopAppBar(
                title = { Text("Followers of $username") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        // Pull-to-refresh wrapper for swipe reload
        SwipeRefresh(
            state = refreshState,
            onRefresh = { loadFollowers() }
        ) {
            when {

                // Show error message if API call failed
                error.isNotEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(error, color = MaterialTheme.colorScheme.error)
                    }
                }

                // loading spinner if data is still being fetched
                loading && followers.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                //  Display the list of followers
                else -> {
                    LazyColumn(modifier = Modifier.padding(padding)) {
                        items(followers) { follower ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {

                                        // Navigate to the selected follower's profile
                                        navController.navigate("main/${follower.login}")
                                    }
                                    .padding(8.dp)
                            ) {
                                Image(
                                    painter = rememberImagePainter(follower.avatarUrl),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(follower.login)
                            }
                        }
                    }
                }
            }
        }
    }
}
