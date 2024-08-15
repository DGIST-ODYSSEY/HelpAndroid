package com.dlrjsgml.help.feature.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SingInState(
    val id: String = "",
    val pw: String = "",
)
sealed interface SignInSideEffect {
    data object LoginSuccess : SignInSideEffect
    data object LoginFailure : SignInSideEffect
}
class LoginViewModel : ViewModel() {
    private val _uiEffect = MutableSharedFlow<SignInSideEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(SingInState())
    val uiState = _uiState.asStateFlow()


    fun updateId(id: String) {
        _uiState.update { it.copy(id = id) }
    }

    fun updatePw(pw: String) {
        _uiState.update { it.copy(pw = pw) }
    }
}