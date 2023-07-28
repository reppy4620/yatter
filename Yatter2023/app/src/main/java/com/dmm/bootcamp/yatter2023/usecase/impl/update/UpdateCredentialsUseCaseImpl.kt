package com.dmm.bootcamp.yatter2023.usecase.impl.update

import android.webkit.URLUtil
import com.dmm.bootcamp.yatter2023.domain.model.Me
import com.dmm.bootcamp.yatter2023.domain.repository.AccountRepository
import com.dmm.bootcamp.yatter2023.usecase.update.UpdateCredentialsUseCase
import com.dmm.bootcamp.yatter2023.usecase.update.UpdateCredentialsUseCaseResult
import java.io.File
import java.net.URL


class UpdateCredentialsUseCaseImpl(
    private val accountRepository: AccountRepository
): UpdateCredentialsUseCase {
    override suspend fun execute(
        me: Me,
        newDisplayName: String?,
        newNote: String?,
        newAvatar: File?,
        newHeader: File?
    ): UpdateCredentialsUseCaseResult {
        if (newDisplayName == null && newNote == null && newAvatar == null && newHeader == null) {
            return UpdateCredentialsUseCaseResult.Success
        }

        return try {
            accountRepository.update(
                me = me,
                newDisplayName = newDisplayName ?: me.displayName,
                newNote = newNote ?: me.note,
                newAvatar = newAvatar,
                newHeader = newHeader
            )
            UpdateCredentialsUseCaseResult.Success
        } catch (e: Exception) {
            UpdateCredentialsUseCaseResult.Failure.OtherError(e)
        }
    }
}