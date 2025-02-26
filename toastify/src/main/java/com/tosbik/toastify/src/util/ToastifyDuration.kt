package com.tosbik.toastify.src.util

sealed class ToastifyDuration(val time: Long){
    data object XSHORT : ToastifyDuration(1000L)
    data object SHORT: ToastifyDuration(3000L)
    data object NORMAL: ToastifyDuration(4500L)
    data object LONG : ToastifyDuration(7000L)
    data object XLONG: ToastifyDuration(10000L)
    data object INFINITE: ToastifyDuration(6000000L)

    companion object {
        const val START = 300L
    }
}