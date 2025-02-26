package com.tosbik.toastify.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tosbik.toastify.src.ui.Toastify
import com.tosbik.toastify.src.ui.rememberToastifyState
import com.tosbik.toastify.src.util.ToastifyDuration
import com.tosbik.toastify.src.util.ToastifyPosition
import com.tosbik.toastify.src.util.ToastifyType
import com.tosbik.toastify.ui.theme.MyappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                HomeScreen()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val toastifySuccessState = rememberToastifyState()
    val toastifyWarningState = rememberToastifyState()
    val toastifyErrorState = rememberToastifyState()


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                toastifySuccessState.showToastify(
                    title = "Success!",
                    content = "Sorry! There was a problem with your request.",
                    duration = ToastifyDuration.SHORT,
                )
            }) { Text("Show Success Toastify") }

            Button(onClick = {
                toastifyWarningState.showToastify(
                    title = "Warning!",
                    content = "Sorry! There was a problem with your request.",
                    duration = ToastifyDuration.SHORT,
                )
            }) { Text("Show Warning Toastify") }

            Button(onClick = {
                toastifyErrorState.showToastify(
                    content = "Sorry! There was a problem with your request.",
                    duration = ToastifyDuration.SHORT,
                )
            }) { Text("Show Error Toastify") }
        }
    }

    Toastify(
        state = toastifySuccessState,
        position = ToastifyPosition.TOP,
        type = ToastifyType.Success,
    )

    Toastify(
        state = toastifyWarningState,
        position = ToastifyPosition.CENTER,
        type = ToastifyType.Warning,
    )

    Toastify(
        state = toastifyErrorState,
        position = ToastifyPosition.BOTTOM,
        type = ToastifyType.Error,
    )
}


@Preview
@Composable
fun HomeScreenPreview() {
    MyappTheme {
        HomeScreen()
    }
}