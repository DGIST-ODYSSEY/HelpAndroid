package com.dlrjsgml.doparich.root

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.annotation.DrawableRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dlrjsgml.help.R
import com.dlrjsgml.help.feature.auth.login.LoginScreen
import com.dlrjsgml.help.feature.auth.upload.UploadScreen
import com.dlrjsgml.help.feature.home.HomeScreen
import com.dlrjsgml.help.ui.theme.Blue200
import com.dlrjsgml.help.ui.theme.Blue700
import com.dlrjsgml.help.ui.theme.Gray200
import com.dlrjsgml.help.ui.theme.Gray600
import com.dlrjsgml.help.ui.theme.White
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NavGraph(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val sharedPreference = context.getSharedPreferences("userInfo", MODE_PRIVATE)
    val value1 = sharedPreference.getString("id", "데이터 없다1")
    val value2 = sharedPreference.getString("pw", "데이터 없다2")
    val value3 = sharedPreference.getBoolean("isLogin", false)
    val backstackEntry by navController.currentBackStackEntryAsState()
    val selectRoute = backstackEntry?.destination?.route
    var isShowNavBar by remember {
        mutableStateOf(false)
    }

    var isLogined by remember { mutableStateOf(false) }
    // Use LaunchedEffect to perform the login check
    val startScreen = if (isLogined) NavGroup.HOME else NavGroup.LOGIN
    val coroutineScope = rememberCoroutineScope()

    val changeNavBar: (Boolean) -> Unit = {
        coroutineScope.launch {
            isShowNavBar = it
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        Scaffold(
            bottomBar = {
                if (isShowNavBar) {
                    // 전체 Bottom Bar를 감싸는 Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                    ) {
                        // 회색 Border를 가진 Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 0.5.dp,
                                    color = Gray200,
                                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                                )
                                .padding(bottom = 12.dp) // 동그라미 공간을 위한 패딩 추가
                        ) {
                            NavCard(
                                modifier = Modifier
                                    .weight(1f)
                                    .noRippleClickable {
                                        navController.popBackStack()
                                        navController.navigate(NavGroup.HOME)
                                    },
                                resId = R.drawable.ic_home,
                                isSelected = selectRoute == NavGroup.HOME
                            )
                            Spacer(modifier = Modifier.weight(1f)) // 중앙의 동그라미를 위한 공간
                            NavCard(
                                modifier = Modifier
                                    .weight(1f)
                                    .noRippleClickable {
                                        navController.popBackStack()
                                        navController.navigate(NavGroup.USER)
                                    },
                                resId = R.drawable.ic_user,
                                isSelected = selectRoute == NavGroup.USER
                            )
                        }

                        // 중앙의 카메라 아이콘을 포함하는 동그라미 Box
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center) // Row의 중앙에 위치
                                .offset(y = (-24).dp) // 위로 28dp 이동
                                .size(62.dp) // 크기 설정
                                .background(
                                    shape = CircleShape,
                                    brush = Brush.verticalGradient(
                                        listOf(Blue200, Blue700)
                                    )
                                )
                                .clickable {
                                    navController.navigate(NavGroup.UPLOAD)
                                },
                            contentAlignment = Alignment.Center // 아이콘이 Box의 중앙에 위치하도록 설정
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Camera",
                                tint = Color.White, // 아이콘 색상 설정
                                modifier = Modifier.size(28.dp) // 아이콘 크기 설정
                            )
                        }
                    }
                }
            }
        )

        {
            NavHost(
                modifier = Modifier
                    .padding(it),
                navController = navController,
                startDestination = if(value3){NavGroup.HOME}else{NavGroup.LOGIN},
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(NavGroup.LOGIN) {
                    LoginScreen(navBottomVisible = changeNavBar, navController)
                }
//                composable(NavGroup.ACCOUNT) {
//                    AccountScreen(navController)
//                }
                composable(NavGroup.UPLOAD){
                    UploadScreen()
                }
                composable(
                    NavGroup.HOME
                ) {
                    HomeScreen(
                        navBottomVisible = changeNavBar, navController = navController
                    )
                }
//                composable(route = NavGroup.BOARD
//                ) {
//                    BoardScreen(navController)
//                }
//
//                composable(NavGroup.USER) {
//                    MyScreen(navController)
//                }
//                composable(NavGroup.WRITE) {
//                    WriteScreen(navController)
//                }
//                composable("${NavGroup.DETAIL}/{id}") { backStackEntry ->
//                    val id = backStackEntry.arguments?.getString("id")
//                    if (id != null) {
//                        DetailScreen(id = id.toLong(), navController = navController)
//                    }
//                }

            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) { onClick() }
}

@Composable
fun NavCard(
    modifier: Modifier = Modifier,
    @DrawableRes resId: Int,
    isSelected: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (isSelected) Blue700 else Gray600)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}