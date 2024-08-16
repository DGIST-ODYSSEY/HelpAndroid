package com.dlrjsgml.help.feature.signup

import androidx.lifecycle.ViewModel
import com.dlrjsgml.help.feature.auth.login.SingInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SingUpInsState(
    val id: String = "",
    val pw: String = "",
    val name: String = ""
)

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SingUpInsState())
    val uiState = _uiState.asStateFlow()


    fun updateName(name : String){
        _uiState.update { it.copy(name = name) }
    }

    fun updateId(id: String) {
        _uiState.update { it.copy(id = id) }
    }

    fun updatePw(pw: String) {
        _uiState.update { it.copy(pw = pw) }
    }
}
