package com.dmm.bootcamp.yatter2023.domain.repository

import android.net.Uri
import com.dmm.bootcamp.yatter2023.domain.model.Account
import com.dmm.bootcamp.yatter2023.domain.model.Me
import com.dmm.bootcamp.yatter2023.domain.model.Password
import com.dmm.bootcamp.yatter2023.domain.model.Username
import java.io.File
import java.net.URL

interface AccountRepository {
  suspend fun findMe(): Me?

  suspend fun findByUsername(username: Username): Account?

  suspend fun create(
    username: Username,
    password: Password
  ): Me

  suspend fun update(
    me: Me,
    newDisplayName: String?,
    newNote: String?,
    newAvatar: File?,
    newHeader: File?
  ): Me
}
