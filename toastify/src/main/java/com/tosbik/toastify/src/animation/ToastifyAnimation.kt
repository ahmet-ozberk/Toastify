package com.tosbik.toastify.src.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.Spring

object ToastifyAnimations {
    fun tweenSpec(duration: Int) = tween<Float>(
        durationMillis = duration,
        easing = FastOutSlowInEasing
    )

    fun springSpec() = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
}