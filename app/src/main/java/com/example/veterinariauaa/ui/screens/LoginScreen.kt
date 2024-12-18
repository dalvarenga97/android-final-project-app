package com.example.veterinariauaa.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.veterinariauaa.data.AuthRepository
import com.example.veterinariauaa.data.DefaultAppContainer
import com.example.veterinariauaa.model.LoginResponse
import com.example.veterinariauaa.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val authRepository: AuthRepository = DefaultAppContainer().authRepository
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineMedium, // Tipografía de Material 3
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error, // Colores de Material 3
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    errorMessage = "Por favor, completa todos los campos"
                } else {
                    errorMessage = ""
                    // Launch a coroutine to handle the login process
                    CoroutineScope(Dispatchers.IO).launch {
                        val user = User(username, password)
                        val response: LoginResponse? // Declare response outside try-catch
                        try {
                            response = authRepository.login(user) // Move this inside try
                        } catch (e: Exception) {
                            // Update the error message on the main thread
                            withContext(Dispatchers.Main) {
                                errorMessage = "Error de inicio de sesión: ${e.message}"
                            }
                            return@launch // Exit the coroutine if there's an error
                        }
                        
                        // Check if the response is null or access_token is missing
                        if (response == null || response.access_token.isEmpty()) {
                            withContext(Dispatchers.Main) {
                                errorMessage = "Error de inicio de sesión: Token de acceso no recibido"
                            }
                            return@launch // Exit the coroutine if there's an error
                        }
                        
                        // Store the access token securely on the main thread
                        withContext(Dispatchers.Main) {
                            storeAccessToken(response.access_token, context)
                            navController.navigate("home")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }
}

// Function to store the access token securely
private fun storeAccessToken(token: String, context: Context) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("access_token", token)
        apply()
    }
}
