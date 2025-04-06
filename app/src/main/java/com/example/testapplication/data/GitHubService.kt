package com.example.testapplication.data


import com.example.testapplication.model.User
import com.example.testapplication.model.Follower
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<Follower>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<Follower>
}