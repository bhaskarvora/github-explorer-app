//package com.example.testapplication
//
//
//import com.example.testapplication.ui.MainScreen
//
//
//import com.example.testapplication.ui.FollowersScreen
//import com.example.testapplication.ui.FollowingScreen
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.testapplication.ui.theme.TestApplicationTheme
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//
//import androidx.compose.ui.unit.dp
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            TestApplicationTheme {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "main") {
//                    composable("main") { MainScreen(navController) }
//
//                    composable("followers/{username}") {backStackEntry ->
//                        val username= backStackEntry.arguments?.getString("username") ?: ""
//                        FollowersScreen(navController, username)
//                    }
//                    composable("following/{username}") {backStackEntry ->
//                        val username = backStackEntry.arguments?.getString("username")?:""
//                        FollowingScreen(navController,username)
//                    }
//                }
//            }
//
//        }
//        }
//    }
//
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    var text by remember { mutableStateOf("Hello $name!") }
//
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = text,
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = { text = "You clicked the button!" }) {
//            Text("Click Me")
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestApplicationTheme {
//        Greeting("Android")
//    }
//}


package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.navArgument

import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.testapplication.ui.*
import com.example.testapplication.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController = navController)
                    }
                    composable(
                        route = "main/{username}",
                        arguments = listOf(navArgument("username") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        MainScreen(navController = navController, initialUsername = username)
                    }
                    composable(
                        route = "followers/{username}",
                        arguments = listOf(navArgument("username") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        FollowersScreen(navController = navController, username = username)
                    }
                    composable(
                        route = "following/{username}",
                        arguments = listOf(navArgument("username") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        FollowingScreen(navController = navController, username = username)
                    }
                }
            }
        }
    }
}
