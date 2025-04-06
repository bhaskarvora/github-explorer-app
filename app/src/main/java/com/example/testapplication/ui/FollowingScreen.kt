
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
fun FollowingScreen(navController: NavController, username: String) {

    // Holds the list of users the given user is following
    var following by remember { mutableStateOf<List<Follower>>(emptyList()) }

    // Store error message
    var error by remember { mutableStateOf("") }

    // Controls loading state here
    var loading by remember { mutableStateOf(true) }

    // Coroutine scope for making API calls
    val scope = rememberCoroutineScope()

    // Here State for swipe-to-refresh functionality
    val refreshState = rememberSwipeRefreshState(isRefreshing = loading)

    // Loads following list from GitHub API
    fun loadFollowing() {
        scope.launch {
            try {
                loading = true
                delay(3000)
                following = RetrofitInstance.api.getFollowing(username)
                error = ""
            } catch (e: Exception) {
                error = "Failed to load following"
            } finally {
                loading = false
            }
        }
    }

    // Automatically load data when the Composable first launches
    LaunchedEffect(username) {
        loadFollowing()
    }

    Scaffold(
        topBar = {

            // App bar with back button
            TopAppBar(
                title = { Text("Following of $username") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        // Wrap content in SwipeRefresh for pull-to-refresh feature
        SwipeRefresh(
            state = refreshState,
            onRefresh = { loadFollowing() }
        ) {
            when {

                // Show error message if loading fails
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

                loading && following.isEmpty() -> {

                    // Show loading indicator while data is being fetched
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                // Show list of users the current user is following

                else -> {
                    LazyColumn(modifier = Modifier.padding(padding)) {
                        items(following) { user ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {

                                        // Navigate to selected user's profile
                                        navController.navigate("main/${user.login}")
                                    }
                                    .padding(8.dp)
                            ) {
                                Image(
                                    painter = rememberImagePainter(user.avatarUrl),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(user.login)
                            }
                        }
                    }
                }
            }
        }
    }
}
