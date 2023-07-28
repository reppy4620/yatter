package com.dmm.bootcamp.yatter2023.auth

import com.dmm.bootcamp.yatter2023.domain.model.Me

interface TokenProvider {
  suspend fun provide(): String

  suspend fun provideFromMe(me: Me): String
}