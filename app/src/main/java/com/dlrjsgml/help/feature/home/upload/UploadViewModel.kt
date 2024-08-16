package com.dlrjsgml.help.feature.home.upload

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import com.dlrjsgml.help.feature.home.HomeViewModel
import com.dlrjsgml.help.ui.theme.Blue800
import com.dlrjsgml.help.ui.theme.Gray200
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class UpLoadState(
    val title: String = "",
    val content: String = "",
    val locate: String = "대구시 달성군",
    var like: Int = 0,
    var isLiked: Boolean = false,
)

class UploadViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(UpLoadState())
    val uiState = _uiState.asStateFlow()

    // 사진을 저장할 StateFlow
    private val _photo: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val photo: StateFlow<Bitmap?> = _photo

    // 사진을 메모리에 저장하는 함수
    fun savePhoto(bitmap: Bitmap) {
        _photo.value = bitmap
    }

    // 사진을 메모리에서 제거하는 함수
    fun removePhoto() {
        _photo.value = null
    }

    // 게시글을 서버로 업로드하고 HomeViewModel에 추가하는 함수


fun updateTitle(title: String) {
    _uiState.update { it.copy(title = title) }
}

fun updateContent(content: String) {
    _uiState.update { it.copy(content = content) }
}
}
