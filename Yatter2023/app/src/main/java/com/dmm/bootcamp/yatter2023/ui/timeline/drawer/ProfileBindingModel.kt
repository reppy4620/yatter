package com.dmm.bootcamp.yatter2023.ui.timeline.drawer

data class ProfileBindingModel(
    val username: String,
    val displayName: String,
    val avatar: String?,
    val followingCount: Int,
    val followerCount: Int
)
