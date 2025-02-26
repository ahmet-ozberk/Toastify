package com.tosbik.toastify.src.util

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToastifyState(
    private val coroutineScope: CoroutineScope
) {
    private val _title = mutableStateOf<String?>(null)
    val title: State<String?> = _title

    private val _content = mutableStateOf<String?>(null)
    val content: State<String?> = _content

    var updateState by mutableStateOf(false)
        private set

    private val _duration = mutableStateOf<ToastifyDuration>(ToastifyDuration.SHORT)
    private val duration: State<ToastifyDuration> = _duration

    private val _position = mutableStateOf<ToastifyPosition>(ToastifyPosition.TOP)
    val position: State<ToastifyPosition> = _position

    private var toastJob: Job? = null

    fun showToastify(
        title: String? = null,
        content: String,
        duration: ToastifyDuration = ToastifyDuration.SHORT
    ) {
        if(updateState){
            toastJob?.cancel()
            coroutineScope.launch {
                hideToastify()
                delay(ToastifyDuration.START)
                _title.value = title
                _content.value = content
                _duration.value = duration
                updateState = true

                if (duration != ToastifyDuration.INFINITE) {
                    toastJob = coroutineScope.launch {
                        delay(ToastifyDuration.START + duration.time)
                        hideToastify()
                    }
                }
            }
        }else{
            toastJob?.cancel()

            _title.value = title
            _content.value = content
            _duration.value = duration
            updateState = true

            if (duration != ToastifyDuration.INFINITE) {
                toastJob = coroutineScope.launch {
                    delay(ToastifyDuration.START + duration.time)
                    hideToastify()
                }
            }
        }
    }

    fun hideToastify() {
        toastJob?.cancel()
        _title.value = null
        _content.value = null
        updateState = false
    }

    fun setPosition(position: ToastifyPosition) {
        _position.value = position
    }

    fun getCurrentDuration(): ToastifyDuration = duration.value
}