package com.tosbik.toastify.src.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tosbik.toastify.src.util.ToastifyDuration
import com.tosbik.toastify.src.util.ToastifyPosition
import com.tosbik.toastify.src.util.ToastifyState
import com.tosbik.toastify.src.util.ToastifyType
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun ToastifyContent(
    state: ToastifyState,
    position: ToastifyPosition,
    paddingValues: PaddingValues,
    type: ToastifyType,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onShow: () -> Unit = {},
) {
    var isVisible by remember { mutableStateOf(state.updateState) }
    val scope = rememberCoroutineScope()

    fun positionArrangement(): Arrangement.Vertical {
        return when (position) {
            ToastifyPosition.TOP -> Arrangement.Top
            ToastifyPosition.BOTTOM -> Arrangement.Bottom
            ToastifyPosition.CENTER -> Arrangement.Center
        }
    }

    LaunchedEffect(state.updateState) {
        state.setPosition(position)
        if (state.getCurrentDuration() != ToastifyDuration.INFINITE) {
            scope.launch {
                delay(ToastifyDuration.START + state.getCurrentDuration().time)
                delay(ToastifyDuration.START)
                state.hideToastify()
                scope.cancel()
            }
        }

        if(state.updateState.not()){
            scope.cancel()
        }

    }

    DisposableEffect(Unit) {
        onDispose {
            isVisible = false
            scope.cancel()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
            .padding(paddingValues),
        verticalArrangement = positionArrangement(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = state.updateState,
            enter = fadeIn(
                animationSpec = tween(ToastifyDuration.START.toInt())
            ) + position.enterTransition,
            exit = fadeOut(animationSpec = tween(ToastifyDuration.START.toInt())) + position.exitTransition
        ) {
            ToastifyModified(
                state = state,
                type = type,
                onDismiss = onDismiss,
                onShow = onShow,
                modifier = modifier,
            )
        }
    }
}