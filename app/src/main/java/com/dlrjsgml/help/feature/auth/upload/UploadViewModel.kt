package com.dlrjsgml.help.feature.auth.upload

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class UpLoadState(
    val title: String = "",
    val content: String = "",
    val photo: Bitmap? = null,
)

class UploadViewModel : ViewModel() {
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

    // 게시글을 서버로 업로드하는 함수 (예제)
    fun uploadPost(title: String, content: String) {
        _photo.value?.let { bitmap ->
            // 서버로 제목, 내용, 사진을 업로드하는 로직을 여기에 작성
            // 예: uploadToServer(title, content, bitmap)
        }
    }

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateContent(content: String) {
        _uiState.update { it.copy(content = content) }
    }
}
