package com.tosbik.toastify.src.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.tosbik.toastify.src.util.ToastifyState
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberToastifyState(
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): ToastifyState {
    val state by remember { mutableStateOf(ToastifyState(coroutineScope)) }
    return state
}