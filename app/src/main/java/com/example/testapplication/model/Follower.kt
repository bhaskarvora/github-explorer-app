package com.example.testapplication.model


import com.google.gson.annotations.SerializedName

data class Follower(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
