package ru.netology.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ru.netology.vknewsclient.ui.theme.ActivityResultTest
import ru.netology.vknewsclient.ui.theme.MainScreen
import ru.netology.vknewsclient.ui.theme.VkNewsClientTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                ActivityResultTest()
            }
        }
    }
}

