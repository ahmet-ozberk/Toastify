# Toastify
This is a customized snackbar package. With this package you can easily inform your users. 

## Demo Video
[Screen Recording](https://github.com/user-attachments/assets/c3790024-01b1-44f9-9535-b7aeb03649bc)

## Features
- Info type toastify
- Success type toastify
- Warning type toastify
- Error type toastify

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

## How to Install?
1. Open your Project's `settings.gradle` file.
2. Add the following...
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
3. Go to build.gradle of app module
4. Add the following dependency:
```
	dependencies {
	        implementation 'com.github.ahmet-ozberk:toastify:1.0.0'
	}
```
5. Enjoy using Toastify components

