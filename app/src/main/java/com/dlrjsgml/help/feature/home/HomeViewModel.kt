package com.dlrjsgml.help.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dlrjsgml.help.feature.all.BoardItem
import com.dlrjsgml.help.feature.home.upload.UpLoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class HomeViewModel : ViewModel() {
    private val _boardItem = MutableStateFlow(BoardItem())
    val boardItem: StateFlow<BoardItem> = _boardItem.asStateFlow()

    fun addPost(post: UpLoadState) {
        _boardItem.update { currentBoardItem ->
            currentBoardItem.copy(boards = currentBoardItem.boards + post)
        }
        Log.d("나나", "${boardItem.value.boards}");

    }
    fun uploadPost(title: String, content: String) {
        val newPost = UpLoadState(
            title = title,
            content = content,
        )
        Log.d("카", "$newPost");
    }
    fun getPosts(): List<UpLoadState> {
        return _boardItem.value.boards
    }
    fun updateLike(index: Int, isLiked: Boolean) {
        _boardItem.update { currentBoardItem ->
            val updatedBoards = currentBoardItem.boards.toMutableList()
            val updatedItem = updatedBoards[index].copy(
                isLiked = isLiked,
                like = updatedBoards[index].like + if (isLiked) 1 else -1
            )
            updatedBoards[index] = updatedItem
            currentBoardItem.copy(boards = updatedBoards)
        }
    }
}
