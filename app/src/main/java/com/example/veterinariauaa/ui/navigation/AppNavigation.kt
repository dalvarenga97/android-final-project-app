package com.example.veterinariauaa.ui.navigation


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.veterinaria.ui.screens.AppointmentsScreen
import com.example.veterinariauaa.data.AppointmentsRepository
import com.example.veterinariauaa.data.PetsRepository
import com.example.veterinariauaa.data.ServicesRepository
import com.example.veterinariauaa.model.Pet
import com.example.veterinariauaa.model.Service
import com.example.veterinariauaa.ui.screens.*


@Composable
fun AppNavigation(
    navController: NavHostController,
    petsRepository: PetsRepository,
    appointmentsRepository: AppointmentsRepository,
    servicesRepository: ServicesRepository
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("pets") { PetsScreen(navController, petsRepository) }
        composable("appointments") {
            val context = LocalContext.current
            val accessToken = getAccessToken(context)

            var petsList by remember { mutableStateOf(listOf<Pet>()) }
            LaunchedEffect(Unit) {
                petsList = petsRepository.getPets("Bearer $accessToken")
            }
            var servicesList by remember { mutableStateOf(listOf<Service>()) }
            LaunchedEffect(Unit) {
                servicesList = servicesRepository.getServices("Bearer $accessToken")
            }

            AppointmentsScreen(
                appointmentsRepository,
                petsList = petsList,
                servicesList = servicesList
            )
        }

        composable("services") { ServicesScreen(servicesRepository) }
        composable("profile") { PerfilScreen(navController)}
        }
}

private fun getAccessToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("access_token", null)
}
