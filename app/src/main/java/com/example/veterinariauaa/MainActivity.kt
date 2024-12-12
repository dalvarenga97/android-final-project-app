package com.example.veterinariauaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.veterinariauaa.repository.CitaRepository
import com.example.veterinariauaa.repository.MascotaRepository
import com.example.veterinariauaa.repository.ServicioRepository
import com.example.veterinariauaa.ui.navigation.AppNavigation
import com.example.veterinariauaa.ui.theme.VeterinariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeterinariaTheme {
                val navController = rememberNavController()
                val mascotaRepository = MascotaRepository()
                val citaRepository = CitaRepository()
                val servicioRepository = ServicioRepository()

                AppNavigation(
                    navController = navController,
                    mascotaRepository = mascotaRepository,
                    citaRepository = citaRepository,
                    servicioRepository = servicioRepository
                )
            }
        }
    }
}
