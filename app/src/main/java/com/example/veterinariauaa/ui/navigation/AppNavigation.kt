package com.example.veterinariauaa.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.veterinaria.ui.screens.CitasScreen
import com.example.veterinariauaa.repository.CitaRepository
import com.example.veterinariauaa.repository.MascotaRepository
import com.example.veterinariauaa.repository.ServicioRepository
import com.example.veterinariauaa.ui.screens.*


@Composable
fun AppNavigation(
    navController: NavHostController,
    mascotaRepository: MascotaRepository,
    citaRepository: CitaRepository,
    servicioRepository: ServicioRepository
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("mascotas") { MascotasScreen(navController, mascotaRepository) }
        composable("citas") {
            // Observamos el flujo de mascotas
            val listaMascotas by mascotaRepository.getMascotas().collectAsState(initial = emptyList())

            // Pasamos la lista de mascotas a CitasScreen
            CitasScreen(citaRepository = citaRepository, listaMascotas = listaMascotas)
        }
        composable("servicios") { ServiciosScreen(servicioRepository) }
        composable("perfil") { PerfilScreen(navController)}
        }
}
