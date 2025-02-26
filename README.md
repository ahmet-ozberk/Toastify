# Toastify
This is a customized snackbar package. With this package you can easily inform your users. 

## Usage Example
```kotlin
@Composable
fun ExampleScreen() {
    val toastifySuccessState = rememberToastifyState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {

            // Start Toastify 🎬
            toastifySuccessState.showToastify(
                title = "Success!",
                content = "This is Success toastify!",
                duration = ToastifyDuration.SHORT,
            )

        }) { Text("Show Success Toastify") }
    }

    // Success Toastify
    Toastify(
        state = toastifySuccessState,
        position = ToastifyPosition.TOP,
        type = ToastifyType.Success,
    )
}
```

## Features
- Info type toastify
- Success type toastify
- Warning type toastify
- Error type toastify

## Demo Video
[Screen Recording](https://github.com/user-attachments/assets/c3790024-01b1-44f9-9535-b7aeb03649bc)

