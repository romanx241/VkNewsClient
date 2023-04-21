package ru.netology.vknewsclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import ru.netology.vknewsclient.ui.theme.MainScreen
import ru.netology.vknewsclient.ui.theme.VkNewsClientTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                val someState = remember {
                    mutableStateOf(true)

                }
                Log.d("MainActivity", "Recomposition : ${someState.value}")

                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    when (it) {
                        is VKAuthenticationResult.Success -> {
                            Log.d("MainActivity", "Success auth")
                        }
                        is VKAuthenticationResult.Failed -> {
                            Log.d("MainActivity", "Failed auth")
                        }
                    }
                }
                LaunchedEffect(key1 = Unit) {
                    Log.d("MainActivity", "LaunchedEffect")
            }
            SideEffect {
                Log.d("MainActivity", "SideEffect")
            }
                Button(onClick = { someState.value = !someState.value }) {
                    Text(text = "Chage state")

                    
                }
            }
        }
    }
}


