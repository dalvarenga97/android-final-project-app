package com.example.veterinaria.ui.screens

import android.app.DatePickerDialog
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
import com.example.veterinariauaa.model.Cita
import com.example.veterinariauaa.model.Mascota
import com.example.veterinariauaa.model.Servicio
import com.example.veterinariauaa.repository.CitaRepository
import java.util.*

@Composable
fun CitasScreen(
    citaRepository: CitaRepository,
    listaMascotas: List<Mascota>,
    listaServicios: List<Servicio> // Lista de servicios
) {
    val citas by citaRepository.getCitas().collectAsState(initial = emptyList())
    var selectedMascota by remember { mutableStateOf<Mascota?>(null) }
    var selectedServicio by remember { mutableStateOf<Servicio?>(null) }
    var expandedMascota by remember { mutableStateOf(false) }
    var expandedServicio by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val openDatePicker = remember { mutableStateOf(false) }

    val context = LocalContext.current

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedMascota = true }
                .padding(8.dp)
        ) {
            Text(
                text = selectedMascota?.nombre ?: "Selecciona una mascota",
                style = MaterialTheme.typography.bodyLarge
            )

            DropdownMenu(
                expanded = expandedMascota,
                onDismissRequest = { expandedMascota = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listaMascotas.forEach { mascota ->
                    DropdownMenuItem(
                        text = { Text(mascota.nombre) },
                        onClick = {
                            selectedMascota = mascota
                            expandedMascota = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Servicio
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedServicio = true }
                .padding(8.dp)
        ) {
            Text(
                text = selectedServicio?.nombre ?: "Selecciona un servicio",
                style = MaterialTheme.typography.bodyLarge
            )

            DropdownMenu(
                expanded = expandedServicio,
                onDismissRequest = { expandedServicio = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listaServicios.forEach { servicio ->
                    DropdownMenuItem(
                        text = { Text(servicio.nombre) },
                        onClick = {
                            selectedServicio = servicio
                            expandedServicio = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de fecha
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { openDatePicker.value = true }
                .padding(8.dp)
        ) {
            Text(
                text = if (selectedDate.isNotEmpty()) selectedDate else "Selecciona una fecha",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar cita
        Button(
            onClick = {
                if (selectedServicio != null && selectedMascota != null && selectedDate.isNotEmpty()) {
                    citaRepository.agregarCita(
                        Cita(
                            id = citas.size + 1,
                            mascotaId = selectedMascota!!.id,
                            fecha = selectedDate,
                            motivo = selectedServicio!!.nombre // Usamos el nombre del servicio como motivo
                        )
                    )
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

        // Lista de citas
        LazyColumn {
            items(citas) { cita ->
                Text(
                    text = "Mascota ID: ${cita.mascotaId} - Fecha: ${cita.fecha} - Motivo: ${cita.motivo}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
