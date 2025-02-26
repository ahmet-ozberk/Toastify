package com.tosbik.toastify.src.util

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

sealed class ToastifyPosition(
    val enterTransition: EnterTransition,
    val exitTransition: ExitTransition
) {
    data object TOP :
        ToastifyPosition(
            enterTransition = slideInVertically(
                initialOffsetY = { -it }, animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
            ),
            exitTransition = slideOutVertically(
                targetOffsetY = { -it }, animationSpec = tween(
                    ToastifyDuration.START.toInt()
                )
            )
        )

    data object BOTTOM : ToastifyPosition(
        enterTransition = slideInVertically(
            initialOffsetY = { it }, animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
        ),
        exitTransition = slideOutVertically(
            targetOffsetY = { it }, animationSpec = tween(
                ToastifyDuration.START.toInt()
            )
        )
    )

    data object CENTER : ToastifyPosition(
        enterTransition = scaleIn(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
        ),
        exitTransition = scaleOut(
            animationSpec = tween(
                ToastifyDuration.START.toInt()
            )
        )
    )
}