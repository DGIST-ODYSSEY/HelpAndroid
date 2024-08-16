package com.dlrjsgml.help.feature.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dlrjsgml.help.R
import com.dlrjsgml.help.ui.theme.Blue800
import com.dlrjsgml.help.ui.theme.Gray200
import com.dlrjsgml.help.ui.theme.Gray800
import com.dlrjsgml.help.ui.theme.caption3
import com.dlrjsgml.help.ui.theme.caption3Bold
import com.dlrjsgml.help.ui.theme.content0
import com.dlrjsgml.help.ui.theme.content1
import com.dlrjsgml.help.ui.theme.title1

@Composable
fun MyScreen(navController: NavHostController) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Text(modifier = Modifier.padding(15.dp), text = "나의 쿠폰", style = title1)

        LazyColumn {
            item {
                Couphone(writer = "00식당", content = "00식당 30%할인 쿠폰",   onClick = { /*TODO*/ }) {

                }
                Spacer(modifier = Modifier.height(10.dp))
                Couphone(writer = "OO미용", content = "OO미용 30%할인 쿠폰",   onClick = { /*TODO*/ }) {

                }
            }
        }

    }
}

@Composable
fun Couphone(
    writer: String,
    content: String,
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
            Box(modifier = Modifier.weight(1f))}
    }
}