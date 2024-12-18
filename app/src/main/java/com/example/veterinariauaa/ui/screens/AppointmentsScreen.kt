package com.example.veterinaria.ui.screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.veterinariauaa.data.AppointmentsRepository
import com.example.veterinariauaa.data.DefaultAppContainer
import com.example.veterinariauaa.model.Appointment
import com.example.veterinariauaa.model.Pet
import com.example.veterinariauaa.model.Service
import java.util.*

// Function to retrieve the access token
private fun getAccessToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("access_token", null)
}

@Composable
fun AppointmentsScreen(
    appointmentRepository: AppointmentsRepository,
    petsList: List<Pet>,
    servicesList: List<Service> // Lista de servicios
) {
    val context = LocalContext.current
    val access_token = remember { getAccessToken(context) }
    val appointmentsRepository: AppointmentsRepository = DefaultAppContainer().appointmentsRepository
    var appointments by remember { mutableStateOf(listOf<Appointment>()) }

    LaunchedEffect(Unit) {
        appointments = appointmentsRepository.getAppointments("Bearer $access_token")
    }
    var selectedMascota by remember { mutableStateOf<Pet?>(null) }
    var selectedServicio by remember { mutableStateOf<Service?>(null) }
    var expandedMascota by remember { mutableStateOf(false) }
    var expandedServicio by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val openDatePicker = remember { mutableStateOf(false) }

    // Función para abrir el DatePickerDialog
    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                selectedDate = "$year-${month + 1}-$dayOfMonth"
                openDatePicker.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    if (openDatePicker.value) {
        showDatePickerDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Citas Médicas",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Mascota
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedMascota = true }
                .padding(8.dp),
        ) {
            Box {
                Text(
                    text = selectedMascota?.name ?: "Selecciona una mascota",
                    style = MaterialTheme.typography.bodyLarge
                )

                DropdownMenu(
                    expanded = expandedMascota,
                    onDismissRequest = { expandedMascota = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    petsList.forEach { pet ->
                        DropdownMenuItem(
                            text = { Text(pet.name) },
                            onClick = {
                                selectedMascota = pet
                                expandedMascota = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Servicio
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedServicio = true },
        ) {
            Box {
                Text(
                    text = selectedServicio?.name ?: "Selecciona un servicio",
                    style = MaterialTheme.typography.bodyLarge
                )

                DropdownMenu(
                    expanded = expandedServicio,
                    onDismissRequest = { expandedServicio = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    servicesList.forEach { service ->
                        DropdownMenuItem(
                            text = { Text(service.name) },
                            onClick = {
                                selectedServicio = service
                                expandedServicio = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de fecha
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { openDatePicker.value = true }
                .padding(8.dp),
        ) {
            Box {
                Text(
                    text = if (selectedDate.isNotEmpty()) selectedDate else "Selecciona una fecha",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar appointment
        Button(
            onClick = {
                if (selectedServicio != null && selectedMascota != null && selectedDate.isNotEmpty()) {
//                    appointmentRepository.agregarAppointment(
//                        Appointment(
//                            id = appointments.size + 1,
//                            pet = selectedMascota!!.name,
//                            fecha = selectedDate,
//                            motivo = selectedServicio!!.nombre // Usamos el nombre del servicio como motivo
//                        )
//
//                    )
                    selectedServicio = null
                    selectedMascota = null
                    selectedDate = ""

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Cita")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de appointments
        LazyColumn {
            items(appointments) { appointment ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Mascota: ${appointment.petId} - Fecha: ${appointment.date} - Motivo: ${appointment.reason}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}