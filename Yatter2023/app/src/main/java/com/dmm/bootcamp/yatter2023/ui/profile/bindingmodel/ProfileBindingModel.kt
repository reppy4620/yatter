package com.dmm.bootcamp.yatter2023.ui.profile.bindingmodel

data class ProfileBindingModel(
    val username: String,
    val displayName: String,
    val note: String,
    val avatar: String?,
    val header: String?,
    val followingCount: Int,
    val followerCount: Int
)