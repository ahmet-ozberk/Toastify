package com.tosbik.toastify.src.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.tosbik.toastify.R

sealed class ToastifyType(
    val borderColor: Color,
    val backgroundColor: Color,
    val contentColor: Color,
    @DrawableRes val icon: Int
) {
    data object Info : ToastifyType(
        borderColor = Color(0xFF05478A),
        backgroundColor = Color(0xFF0070E0),
        contentColor = Color(0xFFFFFFFF),
        icon = R.drawable.ic_info
    )

    data object Success : ToastifyType(
        borderColor = Color(0xFF004E32),
        backgroundColor = Color(0xFF0C7040),
        contentColor = Color(0xFFFFFFFF),
        icon = R.drawable.ic_success
    )

    data object Warning : ToastifyType(
        borderColor = Color(0xFFCC561E),
        backgroundColor = Color(0xFFEF8D32),
        contentColor = Color(0xFFFFFFFF),
        icon = R.drawable.ic_warning
    )

    data object Error : ToastifyType(
        borderColor = Color(0xFF9D2333),
        backgroundColor = Color(0xFFC72C41),
        contentColor = Color(0xFFFFFFFF),
        icon = R.drawable.ic_error
    )
}