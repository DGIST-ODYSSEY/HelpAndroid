package com.dlrjsgml.help.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dlrjsgml.help.ui.theme.Blue800
import com.dlrjsgml.help.ui.theme.Gray200
import com.dlrjsgml.help.ui.theme.Gray600
import com.dlrjsgml.help.ui.theme.content1
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    enabled: Boolean = true,
    secured: Boolean = false,
    singleLine: Boolean = true,
    maxLine : Int = 1,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    var isFocused by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }
    val isSecured = secured && !showText
    val isHint = value.isEmpty()

    val borderColor by animateColorAsState(
        targetValue = when {
            !enabled -> Gray200
            isFocused -> Blue800
            else -> Gray600
        },
        label = ""
    )

    Box {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = content1.copy(color = if (enabled) Color.Black else Gray600),
            keyboardOptions = if (isSecured) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            visualTransformation = if (isSecured) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = singleLine,
            enabled = enabled,
            cursorBrush = SolidColor(Blue800),
            maxLines = maxLine,
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp) // 높이 증가 (기존 40.dp에서 48.dp로)
                .background(
                    color = Color.White,
                    shape = shape
                )
                .onFocusChanged { isFocused = it.isFocused }
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = shape
                ),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(horizontal = 16.dp, vertical = 13.dp)) { // 패딩 조정
                    if (isHint) {
                        Text(
                            text = hint,
                            style = content1,
                            color = Gray600
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
