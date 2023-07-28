package com.dmm.bootcamp.yatter2023.ui.edit

import java.io.File

data class EditBindingModel(
    val displayName: String,
    val note: String,
    val avatar: String,
    val header: String,
    val uploadAvatar: File?,
    val uploadHeader: File?
)
