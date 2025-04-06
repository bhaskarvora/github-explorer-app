
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
    var following by remember { mutableStateOf<List<Follower>>(emptyList()) }
    var error by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val refreshState = rememberSwipeRefreshState(isRefreshing = loading)

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

    LaunchedEffect(username) {
        loadFollowing()
    }

    Scaffold(
        topBar = {
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
        SwipeRefresh(
            state = refreshState,
            onRefresh = { loadFollowing() }
        ) {
            when {
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    LazyColumn(modifier = Modifier.padding(padding)) {
                        items(following) { user ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
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
