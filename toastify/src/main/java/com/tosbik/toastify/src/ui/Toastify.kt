package com.tosbik.toastify.src.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tosbik.toastify.src.util.ToastifyDuration
import com.tosbik.toastify.src.util.ToastifyPosition
import com.tosbik.toastify.src.util.ToastifyState
import com.tosbik.toastify.src.util.ToastifyType

@Composable
fun Toastify(
    state: ToastifyState,
    position: ToastifyPosition = ToastifyPosition.TOP,
    type: ToastifyType = ToastifyType.Info,
    paddingValues: PaddingValues = PaddingValues(24.dp),
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onShow: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ToastifyContent(
            state = state,
            position = position,
            type = type,
            paddingValues = paddingValues,
            modifier = modifier,
            onDismiss = onDismiss,
            onShow = onShow,
        )
    }
}