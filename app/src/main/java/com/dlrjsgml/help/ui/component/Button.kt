package com.dlrjsgml.help.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dlrjsgml.help.ui.theme.Blue800
import com.dlrjsgml.help.ui.theme.White
import com.dlrjsgml.help.ui.theme.content1

@Composable
fun HelpButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit,
) {
    Box(

    ) {
        Box(
            modifier = modifier
                .background(
                    color = if (enabled) Blue800 else Color(0xFFF5F6F8),
                    shape = shape
                ).clickable {
                    onClick()
                }
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(contentPadding),
                text = text,
                color = if (enabled) White else Color(0xFFCCCCD6),
                style = content1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}