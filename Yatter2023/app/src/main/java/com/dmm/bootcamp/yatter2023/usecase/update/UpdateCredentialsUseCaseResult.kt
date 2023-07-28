package com.dmm.bootcamp.yatter2023.usecase.update

sealed interface UpdateCredentialsUseCaseResult {
    object Success: UpdateCredentialsUseCaseResult
    sealed interface Failure: UpdateCredentialsUseCaseResult {
        object EmptyDisplayName: Failure
        object EmptyNote: Failure
        object EmptyAvatar: Failure
        object EmptyHeader: Failure
        object InvalidUrl: Failure
        data class OtherError(val throwable: Throwable): Failure
    }
}
