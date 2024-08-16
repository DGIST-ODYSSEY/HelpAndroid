package com.dlrjsgml.help.feature.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.feature.main.board.sub.loading.LoadingScreen
import com.dlrjsgml.help.R
import com.dlrjsgml.help.feature.home.upload.UpLoadState
import com.dlrjsgml.help.ui.theme.Blue700
import com.dlrjsgml.help.ui.theme.Blue800
import com.dlrjsgml.help.ui.theme.Gray200
import com.dlrjsgml.help.ui.theme.Gray800
import com.dlrjsgml.help.ui.theme.caption3
import com.dlrjsgml.help.ui.theme.caption3Bold
import com.dlrjsgml.help.ui.theme.content0
import com.dlrjsgml.help.ui.theme.content1
import com.dlrjsgml.help.ui.theme.title1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var goodBoards: List<UpLoadState> = arrayListOf(
    UpLoadState(
        title = "구지면에 맥도날드가 생겼으면 좋겠어요",
        content = "이공간에 멕도날드가 생겼으면 정말 좋겠어요",
        locate = "대구시 달성군",
        like = 132,
    ),
    UpLoadState(
        title = "대구소프트웨어마이스터고등학교 근처에 음식점이 생겼으면 좋겠어요",
        content = "아파트는 많은데 음식점이 적네요",
        locate = "대구시 달성군",
        like = 92
    ),
    UpLoadState(
        title = "테크노폴리스 근처에 미용실이 생겼으면 좋겠어요",
        content = "사용할 의향 있습니다.",
        locate = "대구시 달성군",
        like = 71
    ),
    UpLoadState(
        title = "디지스트 근처에 치킨집이 생겼으면 좋겠어요",
        content = "dddf",
        locate = "정말 편리할것 같네요",
        like = 0
    ),
    UpLoadState(
        title = "대구소프트웨어마이스터고등학교 근처에 카페가 생겼으면 정말 좋겠어요",
        content = "카페가 정말 부족한것 같아요 ㅠㅠ",
        locate = "대구시 달성군",
        like = 0
    )
)

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),
    navBottomVisible: (Boolean) -> Unit,
) {
    val uiState by viewModel.boardItem.collectAsState()
    LaunchedEffect(key1 = true) {
        navBottomVisible(true)
    }
    // BoardItem 상태를 수집

    var contentToShow by remember { mutableStateOf<@Composable () -> Unit>({}) }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            contentToShow = {
                LoadingScreen() }
            delay(1500L)
            contentToShow={ BoardList(boardList = goodBoards, navController = navController)}
        }
    }


    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Text(modifier = Modifier.padding(15.dp), text = "필요해요!", style = title1)
        contentToShow()
    }
}

@Composable
fun ContentCards(
    writer: String,
    content: String,
    title: String,
    like: Int,
    isLiked: Boolean,
    onClick: () -> Unit,
    heartClick: () -> Unit,
) {
    // `heartColors` 상태를 직접 설정
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) { onClick() }
            .padding(horizontal = 10.dp)
            .background(color = Color.White)
            .padding(horizontal = 5.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 27.dp)
                .padding(horizontal = 15.dp),
            text = title,
            style = content1,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 15.dp),
            text = content,
            style = content0,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Gray200)
        )
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier
                .padding(top = 3.dp, bottom = 23.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = writer,
                style = caption3Bold,
                color = Gray800
            )
            Box(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 20.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            heartClick()
                        },
                    painter = painterResource(id = R.drawable.ic_like_board),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(if (isLiked) Blue800 else Gray200) // 설정된 colorFilter 적용
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = like.toString(),
                    style = caption3
                )
            }
        }
    }
}



@Composable
fun BoardList(
    boardList: List<UpLoadState>,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),
) {
    LazyColumn {
        items(boardList.size) { index ->
            Spacer(modifier = Modifier.height(8.dp))
            val item = boardList[index]
            ContentCards(
                title = item.title,
                writer = item.locate,
                content = item.content,
                like = item.like,
                isLiked = item.isLiked, // 수정된 부분
                onClick = {},
                heartClick = {
                    viewModel.updateLike(index, !item.isLiked)
                }
            )
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}
