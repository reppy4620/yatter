package com.dmm.bootcamp.yatter2023.ui.timeline.drawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.service.GetMeService
import com.dmm.bootcamp.yatter2023.domain.service.LogoutService
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrawerViewModel(
    private val getMeService: GetMeService,
    private val logoutService: LogoutService
): ViewModel() {

    private val _uiState: MutableStateFlow<DrawerUiState> =
        MutableStateFlow(DrawerUiState.empty())
    val uiState: StateFlow<DrawerUiState> = _uiState

    private val _navigateToProfile: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToProfile: LiveData<Unit> = _navigateToProfile

    private val _navigateToLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    private suspend fun fetchMe() {
        val me = getMeService.execute() ?: return
        val snapShotBindingModel = uiState.value.bindingModel
        _uiState.update {
            it.copy(
                bindingModel = snapShotBindingModel.copy(
                    username = me.username.value,
                    displayName = me.displayName ?: "",
                    avatar = me.avatar.toString(),
                    followingCount = me.followingCount,
                    followerCount = me.followerCount
                )
            )
        }
    }

    fun onResume() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            fetchMe()
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickProfile() {
        _navigateToProfile.value = Unit
    }

    fun onClickLogout() {
        viewModelScope.launch {
            logoutService.execute()
            _navigateToLogin.value = Unit
        }
    }
}