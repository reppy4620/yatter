package com.dmm.bootcamp.yatter2023.usecase.update

import com.dmm.bootcamp.yatter2023.domain.model.Me
import java.io.File

interface UpdateCredentialsUseCase {
    suspend fun execute(
        me: Me,
        newDisplayName: String?,
        newNote: String?,
        newAvatar: File?,
        newHeader: File?
    ): UpdateCredentialsUseCaseResult
}
