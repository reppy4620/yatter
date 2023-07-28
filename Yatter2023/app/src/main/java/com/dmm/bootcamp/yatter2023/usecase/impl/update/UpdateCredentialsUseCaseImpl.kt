package com.dmm.bootcamp.yatter2023.usecase.impl.update

import android.webkit.URLUtil
import com.dmm.bootcamp.yatter2023.domain.model.Me
import com.dmm.bootcamp.yatter2023.domain.repository.AccountRepository
import com.dmm.bootcamp.yatter2023.usecase.update.UpdateCredentialsUseCase
import com.dmm.bootcamp.yatter2023.usecase.update.UpdateCredentialsUseCaseResult
import java.net.URL


class UpdateCredentialsUseCaseImpl(
    private val accountRepository: AccountRepository
): UpdateCredentialsUseCase {
    override suspend fun execute(
        me: Me,
        newDisplayName: String?,
        newNote: String?,
        newAvatar: String?,
        newHeader: String?
    ): UpdateCredentialsUseCaseResult {
        if (newDisplayName == null && newNote == null && newAvatar == null && newHeader == null) {
            return UpdateCredentialsUseCaseResult.Success
        }
        if (newDisplayName != null) {
            if (newDisplayName.isEmpty()) return UpdateCredentialsUseCaseResult.Failure.EmptyDisplayName
        }
        if (newNote != null) {
            if (newNote.isEmpty()) return UpdateCredentialsUseCaseResult.Failure.EmptyNote
        }
        if (newAvatar != null) {
            if (newAvatar.isEmpty()) return UpdateCredentialsUseCaseResult.Failure.EmptyAvatar
            if (!URLUtil.isValidUrl(newAvatar)) return UpdateCredentialsUseCaseResult.Failure.InvalidUrl
        }
        if (newHeader != null) {
            if (newHeader.isEmpty()) return UpdateCredentialsUseCaseResult.Failure.EmptyHeader
            if (!URLUtil.isValidUrl(newHeader)) return UpdateCredentialsUseCaseResult.Failure.InvalidUrl
        }


        return try {
            accountRepository.update(
                me = me,
                newDisplayName = newDisplayName ?: me.displayName,
                newNote = newNote ?: me.note,
                newAvatar = null,
                newHeader = null
            )
            UpdateCredentialsUseCaseResult.Success
        } catch (e: Exception) {
            UpdateCredentialsUseCaseResult.Failure.OtherError(e)
        }
    }
}