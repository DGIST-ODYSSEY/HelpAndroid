package com.dlrjsgml.help.feature.signup

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.help.feature.auth.login.LoginViewModel
import com.dlrjsgml.help.ui.component.HelpButton
import com.dlrjsgml.help.ui.component.MyTextField
import com.dlrjsgml.help.ui.theme.title1

@Composable
fun SignUpScreen(
    navBottomVisible: (Boolean) -> Unit,
    navController: NavHostController,
    viewModel: SignUpViewModel = viewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(15.dp)) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "회원가입", style = title1)
        Spacer(modifier = Modifier.height(20.dp))
        MyTextField(value = uiState.name, onValueChange = viewModel::updateName, hint = "이름을 입력해주세요")
        Spacer(modifier = Modifier.height(10.dp))
        MyTextField(value = uiState.id, onValueChange = viewModel::updateId, hint = "아이디를 입력해주세요")
        Spacer(modifier = Modifier.height(10.dp))
        MyTextField(value = uiState.pw, onValueChange = viewModel::updatePw, hint = "비밀번호를 입력해주세요", secured = true)
        Spacer(modifier = Modifier.weight(1f))
        HelpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            text = "회원가입",
            enabled = uiState.id.isNotEmpty() && uiState.pw.isNotEmpty(),
            contentPadding = PaddingValues(vertical = 16.5.dp),
            onClick = {
                if(uiState.id.isNotEmpty() && uiState.pw.isNotEmpty() && uiState.name.isNotEmpty()){
                    Toast.makeText(context,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    navController.navigate(NavGroup.LOGIN)
                    Log.d("하하", "dlrjsㅇㅇㅇㅇㅇㅇgml44 Ok");
                }
            }
        )
    }


}