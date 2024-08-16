package com.dlrjsgml.help.feature.home.upload

import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.help.feature.home.HomeViewModel
import com.dlrjsgml.help.feature.home.goodBoards
import com.dlrjsgml.help.ui.component.HelpButton
import com.dlrjsgml.help.ui.component.MyTextField
import com.dlrjsgml.help.ui.theme.title1

@Composable
fun UploadScreen(
    homeViewModel: HomeViewModel, // HomeViewModel 주입
    viewModel: UploadViewModel = viewModel(),
    homeviewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    val photo by viewModel.photo.collectAsState()
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    // 카메라 촬영 후 결과를 처리하는 런처
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap: Bitmap? ->
            bitmap?.let {
                viewModel.savePhoto(it) // ViewModel에 사진 저장
            }
        }
    )

    // 카메라 권한 요청 런처
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                // 권한이 허용된 경우 카메라 촬영 시작
                cameraLauncher.launch(null)
            } else {
                Toast.makeText(context, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    )
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "글쓰기", style = title1)
        // 제목 입력 필드
        Spacer(modifier = Modifier.height(15.dp))

        MyTextField(
            value = uiState.title,
            onValueChange = viewModel::updateTitle,
            hint = "제목을 적어주세요",
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 내용 입력 필드
        MyTextField(
            value = uiState.content,
            onValueChange = viewModel::updateContent,
            hint = "내용을 적어주세요",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .weight(1f),
            maxLine = 10
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 사진 추가 섹션
        if (photo != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = androidx.compose.ui.graphics.Color.LightGray)
            ) {
                Image(
                    bitmap = photo!!.asImageBitmap(),
                    contentDescription = "Captured Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            // 사진 클릭 시 다른 동작을 추가하고 싶다면 여기에 추가
                        }
                )
                IconButton(
                    onClick = { viewModel.removePhoto() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .background(
                            color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Close,
                        contentDescription = "Remove Image",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            HelpButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                text = "사진찍기",
                enabled = true,
                contentPadding = PaddingValues(vertical = 9.5.dp),
                onClick = {
                    cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // 업로드 버튼
        HelpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            text = "글 쓰기",
            enabled = uiState.title.isNotEmpty() && uiState.content.isNotEmpty(),
            contentPadding = PaddingValues(vertical = 9.5.dp),
            onClick = {
                if (uiState.title.isNotEmpty() && uiState.content.isNotEmpty()) {
                    val djdjad = UpLoadState(
                        title = uiState.title,
                        content = uiState.content,
                    )
                    goodBoards = goodBoards + djdjad
                    homeviewModel.addPost(djdjad)
                    navController.navigate(NavGroup.HOME) // HomeScreen으로 돌아가기
                }
            }
        )
    }
}
