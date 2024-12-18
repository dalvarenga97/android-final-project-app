package com.example.veterinariauaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.veterinariauaa.data.AppContainer
import com.example.veterinariauaa.data.DefaultAppContainer
import com.example.veterinariauaa.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    private val appContainer: AppContainer = DefaultAppContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val petsRepository = appContainer.petsRepository
            val appointmentsRepository = appContainer.appointmentsRepository
            val servicesRepository = appContainer.servicesRepository
            AppNavigation(navController, petsRepository, appointmentsRepository, servicesRepository)
        }
    }
}
