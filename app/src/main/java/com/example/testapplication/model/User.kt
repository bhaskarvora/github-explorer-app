package com.example.testapplication.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val name: String?,
    val bio: String?,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("following") val followingCount: Int
)