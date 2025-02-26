package com.tosbik.toastify.src.ui


import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tosbik.toastify.R
import com.tosbik.toastify.src.util.ToastifyDuration
import com.tosbik.toastify.src.util.ToastifyPosition
import com.tosbik.toastify.src.util.ToastifyState
import com.tosbik.toastify.src.util.ToastifyType
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
internal fun ToastifyModified(
    state: ToastifyState,
    type: ToastifyType,
    onDismiss: () -> Unit = {},
    onShow: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    var isClosing by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    var offsetXValue by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(state.updateState) {
        onShow()
        val startTime = System.currentTimeMillis()
        while (progress < 1f && !isClosing) {
            val currentTime = System.currentTimeMillis()
            progress = (currentTime - startTime).toFloat() / state.getCurrentDuration().time
            delay(16)
            if (progress >= 1f) {
                isClosing = true
            }
        }
        if(state.updateState.not()){
            isClosing = true
            coroutineScope.cancel()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            onDismiss()
        }
    }

    val offsetY by animateFloatAsState(
        targetValue = if (isClosing) if (state.position.value == ToastifyPosition.BOTTOM) 100f else -100f else 0f,
        animationSpec = tween(
            durationMillis = ToastifyDuration.START.toInt(),
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            if (isClosing) {
                state.hideToastify()
                onDismiss()
            }
        },
        label = ""
    )

    val alpha by animateFloatAsState(
        targetValue = if (isClosing) 0f else 1f,
        animationSpec = tween(
            durationMillis = ToastifyDuration.START.toInt(),
            easing = FastOutSlowInEasing
        ),
        label = ""
    )


    Row(
        modifier = modifier
            .offset(
                x = offsetXValue.dp,
                y = if (state.position.value == ToastifyPosition.CENTER) 0.dp else offsetY.dp
            )
            .alpha(alpha)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(type.backgroundColor, RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            if (abs(offsetXValue) < 200f) {
                                offsetXValue = 0f
                            } else {
                                isClosing = true
                            }
                        }
                    },
                    onDragCancel = {
                        coroutineScope.launch {
                            offsetXValue = 0f
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        offsetXValue = (offsetXValue + dragAmount).coerceIn(-1000f, 1000f)
                    }
                )
            },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(top = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(32.dp)
                    .background(type.borderColor, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(type.icon),
                    contentDescription = null,
                    tint = type.contentColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_box_element),
                contentDescription = null,
                tint = type.borderColor,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(14.dp)
                    .offset(x = 6.dp, y = (-8).dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Icon(
                painter = painterResource(R.drawable.ic_box_effect),
                contentDescription = null,
                tint = type.borderColor,
                modifier = Modifier.size(32.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            if (state.title.value != null) {
                Text(
                    state.title.value!!,
                    style = MaterialTheme.typography.titleLarge,
                    color = type.contentColor
                )
            }
            Text(
                state.content.value ?: "",
                style = if (state.title.value != null) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodyLarge,
                color = type.contentColor,
                lineHeight = 16.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(end = 8.dp, top = 8.dp)
                .size(28.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(
                            type.borderColor.copy(alpha = 0.3f),
                            CircleShape
                        )
                        .fillMaxHeight(progress)
                )
            }
            ToastifyDismissButton(onDismiss = { isClosing = true })
        }
    }
}
