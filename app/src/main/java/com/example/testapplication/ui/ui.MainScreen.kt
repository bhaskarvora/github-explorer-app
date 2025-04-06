//package com.example.testapplication.ui
//
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.Alignment
//import androidx.navigation.NavController
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.rememberImagePainter
//import androidx.compose.material3.TopAppBar
//import androidx.compose.foundation.border
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.People
//import androidx.compose.material3.Icon
//
//
//import com.example.testapplication.viewmodel.MainViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
//    val username by viewModel.username
//    val user by viewModel.user
//    val error by viewModel.error
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("GitHub Explorer") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedTextField(
//                value = username,
//                onValueChange = { viewModel.onUsernameChange(it) },
//                label = { Text("GitHub Username") }
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = { viewModel.searchUser() }) {
//                Text("Search")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            error.takeIf { it.isNotEmpty() }?.let {
//                Text(it, color = MaterialTheme.colorScheme.error)
//            }
//
//            user?.let {
//                Spacer(modifier = Modifier.height(16.dp))
////                Image(
////                    painter = rememberImagePainter(it.avatarUrl),
////                    contentDescription = "Avatar",
////                    modifier = Modifier.size(120.dp)
////                )
//                Image(
//                    painter = rememberImagePainter(it.avatarUrl),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                )
//                Text("Username: ${it.login}")
//                Text("Name: ${it.name ?: "N/A"}")
//                Text("Bio: ${it.bio ?: "N/A"}")
//                Row {
//                    Text(
//                        text = "${it.followersCount} followers",
//                        modifier = Modifier
//                            .clickable {
//                                navController.navigate("followers/${it.login}")
//                            }
//                            .padding(end = 8.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    )
//                    {
//                        Icon(
//                            imageVector = Icons.Default.People,
//                            contentDescription = "Followers Icon",
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followingCount} following",
//                        modifier = Modifier
//                            .clickable {
//                                navController.navigate("following/${it.login}")
//                            }
//                    )
//                }
//            }
//        }
//    }
//}
//
//package com.example.testapplication.ui
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.People
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.rememberImagePainter
//import com.example.testapplication.viewmodel.MainViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
//    val username by viewModel.username
//    val user by viewModel.user
//    val error by viewModel.error
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("GitHub Explorer") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedTextField(
//                value = username,
//                onValueChange = { viewModel.onUsernameChange(it) },
//                label = { Text("GitHub Username") }
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = { viewModel.searchUser() }) {
//                Text("Search")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            error.takeIf { it.isNotEmpty() }?.let {
//                Text(it, color = MaterialTheme.colorScheme.error)
//            }
//
//            user?.let {
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Image(
//                    painter = rememberImagePainter(it.avatarUrl),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                )
//
//                Text("Username: ${it.login}")
//                Text("Name: ${it.name ?: "N/A"}")
//                Text("Bio: ${it.bio ?: "N/A"}")
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // Followers Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("followers/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Followers Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followersCount} followers",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                // Following Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("following/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Following Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followingCount} following",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//}


// Final code
//package com.example.testapplication.ui
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.People
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.rememberImagePainter
//import com.example.testapplication.viewmodel.MainViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
//    val username by viewModel.username
//    val user by viewModel.user
//    val error by viewModel.error
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("GitHub Explorer") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedTextField(
//                value = username,
//                onValueChange = { viewModel.onUsernameChange(it) },
//                label = { Text("GitHub Username") }
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = { viewModel.searchUser() }) {
//                Text("Search")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            error.takeIf { it.isNotEmpty() }?.let {
//                Text(it, color = MaterialTheme.colorScheme.error)
//            }
//
//            user?.let {
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Image(
//                    painter = rememberImagePainter(it.avatarUrl),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                )
//
//                Text("Username: ${it.login}")
//                Text("Name: ${it.name ?: "N/A"}")
//                Text("Bio: ${it.bio ?: "N/A"}")
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // Followers Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("followers/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Followers Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followersCount} followers",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                // Following Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("following/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Following Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followingCount} following",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//}
//
//package com.example.testapplication.ui
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.People
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.rememberImagePainter
//import com.example.testapplication.viewmodel.MainViewModel
//
//import com.google.accompanist.placeholder.material.placeholder
//import com.google.accompanist.placeholder.material.shimmer
//
//
//
//
//
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
//    val username by viewModel.username
//    val user by viewModel.user
//    val error by viewModel.error
//    val loading = user == null && error.isEmpty()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("GitHub Explorer") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedTextField(
//                value = username,
//                onValueChange = { viewModel.onUsernameChange(it) },
//                label = { Text("GitHub Username") }
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = { viewModel.searchUser() }) {
//                Text("Search")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            error.takeIf { it.isNotEmpty() }?.let {
//                Text(it, color = MaterialTheme.colorScheme.error)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            val shimmerModifier = Modifier.placeholder(
//                visible = loading,
//                highlight = shimmer()
//
//
//
//            )
//
//            if (loading) {
//                Box(
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                        .then(shimmerModifier)
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                repeat(3) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth(0.8f)
//                            .height(20.dp)
//                            .padding(vertical = 4.dp)
//                            .then(shimmerModifier)
//                    )
//                }
//            }
//
//            user?.let {
//                Image(
//                    painter = rememberImagePainter(it.avatarUrl),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                )
//
//                Text("Username: ${it.login}")
//                Text("Name: ${it.name ?: "N/A"}")
//                Text("Bio: ${it.bio ?: "N/A"}")
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // Followers Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("followers/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Followers Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followersCount} followers",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                // Following Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("following/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Following Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followingCount} following",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//}
//
//package com.example.testapplication.ui
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.People
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.rememberImagePainter
//import com.example.testapplication.viewmodel.MainViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
//    val username by viewModel.username
//    val user by viewModel.user
//    val error by viewModel.error
//    val loading by viewModel.loading
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("GitHub Explorer") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedTextField(
//                value = username,
//                onValueChange = { viewModel.onUsernameChange(it) },
//                label = { Text("GitHub Username") }
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = { viewModel.searchUser() }) {
//                Text("Search")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            error.takeIf { it.isNotEmpty() }?.let {
//                Text(it, color = MaterialTheme.colorScheme.error)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (loading) {
//                Box(
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                        .background(Color.LightGray)
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                repeat(3) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth(0.8f)
//                            .height(20.dp)
//                            .padding(vertical = 4.dp)
//                            .background(Color.LightGray)
//                    )
//                }
//            }
//
//            user?.let {
//                Image(
//                    painter = rememberImagePainter(it.avatarUrl),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape)
//                )
//
//                Text("Username: ${it.login}")
//                Text("Name: ${it.name ?: "N/A"}")
//                Text("Bio: ${it.bio ?: "N/A"}")
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // Followers Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("followers/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Followers Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followersCount} followers",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                // Following Row
//                Row(
//                    modifier = Modifier
//                        .clickable { navController.navigate("following/${it.login}") }
//                        .padding(vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.People,
//                        contentDescription = "Following Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "${it.followingCount} following",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//}


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

            error.takeIf { it.isNotEmpty() }?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

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

                // Followers Row
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

                // Following Row
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

