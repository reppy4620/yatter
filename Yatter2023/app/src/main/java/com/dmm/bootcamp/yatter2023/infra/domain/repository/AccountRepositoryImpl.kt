package com.dmm.bootcamp.yatter2023.infra.domain.repository

import android.accounts.AuthenticatorException
import android.content.Context
import android.net.Uri
import com.dmm.bootcamp.yatter2023.auth.TokenProvider
import com.dmm.bootcamp.yatter2023.domain.model.Account
import com.dmm.bootcamp.yatter2023.domain.model.Me
import com.dmm.bootcamp.yatter2023.domain.model.Password
import com.dmm.bootcamp.yatter2023.domain.model.Username
import com.dmm.bootcamp.yatter2023.domain.repository.AccountRepository
import com.dmm.bootcamp.yatter2023.infra.api.YatterApi
import com.dmm.bootcamp.yatter2023.infra.api.json.CreateAccountJson
import com.dmm.bootcamp.yatter2023.infra.domain.converter.AccountConverter
import com.dmm.bootcamp.yatter2023.infra.domain.converter.MeConverter
import com.dmm.bootcamp.yatter2023.infra.pref.MePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.net.URL

class AccountRepositoryImpl(
  private val yatterApi: YatterApi,
  private val mePreferences: MePreferences,
  private val tokenProvider: TokenProvider
) : AccountRepository {
  override suspend fun create(
    username: Username,
    password: Password
  ): Me = withContext(Dispatchers.IO) {
    val accountJson = yatterApi.createNewAccount(
      CreateAccountJson(
        username = username.value,
        password = password.value
      )
    )

    MeConverter.convertToMe(AccountConverter.convertToDomainModel(accountJson))
  }

  override suspend fun findMe(): Me? = withContext(Dispatchers.IO) {
    val username = mePreferences.getUsername() ?: return@withContext null
    if (username.isEmpty()) return@withContext null
    val account = findByUsername(username = Username(username))
    MeConverter.convertToMe(account)
  }

  override suspend fun findByUsername(username: Username): Account = withContext(Dispatchers.IO) {
    val accountJson = yatterApi.getAccountByUsername(username = username.value)
    AccountConverter.convertToDomainModel(accountJson)
  }

  override suspend fun update(
    me: Me,
    newDisplayName: String?,
    newNote: String?,
    newAvatar: File?,
    newHeader: File?
  ): Me {
//    TODO("Not Implemented")
    return try {
      val token = tokenProvider.provide()
      yatterApi.updateCredentials(
        token = token,
        displayName = newDisplayName?.toRequestBody() ?: me.displayName.toString().toRequestBody(),
        note = newNote?.toRequestBody() ?: me.note.toString().toRequestBody(),
        avatar = newAvatar?.let { createMultipartBodyPart(it, "avatar") },
        header = newHeader?.let { createMultipartBodyPart(it, "header") }
      )
      me
    } catch (e: AuthenticatorException) {
      me
    } catch (e: Exception) {
      me
    }
  }

  private fun createMultipartBodyPart(imageFile: File, paramName: String): MultipartBody.Part {
    val requestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(paramName, imageFile.name, requestBody)
  }
}
