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
import com.example.veterinariauaa.repository.CitaRepository
import java.util.*


@Composable
fun CitasScreen(
    citaRepository: CitaRepository,
    listaMascotas: List<Mascota> // Lista de mascotas a mostrar
) {
    val citas by citaRepository.getCitas().collectAsState(initial = emptyList())
    var newCitaMotivo by remember { mutableStateOf("") }
    var selectedMascota by remember { mutableStateOf<Mascota?>(null) }
    var expanded by remember { mutableStateOf(false) } // Controla el menú desplegable
    var selectedDate by remember { mutableStateOf("") } // Para almacenar la fecha seleccionada
    val openDatePicker = remember { mutableStateOf(false) } // Controla el diálogo del DatePicker

    val context = LocalContext.current

    // Función para abrir el DatePickerDialog
    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Convertir la fecha seleccionada a formato String
                selectedDate = "$year-${month + 1}-$dayOfMonth"
                openDatePicker.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    // Mostrar el DatePickerDialog si se ha activado
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

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Mascota usando DropdownMenu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(8.dp)
        ) {
            Text(
                text = selectedMascota?.nombre ?: "Selecciona una mascota",
                style = MaterialTheme.typography.bodyLarge
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listaMascotas.forEach { mascota ->
                    DropdownMenuItem(
                        text = { Text(mascota.nombre) },
                        onClick = {
                            selectedMascota = mascota
                            expanded = false
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

        // Campo de motivo de cita
        TextField(
            value = newCitaMotivo,
            onValueChange = { newCitaMotivo = it },
            label = { Text("Motivo de la nueva cita") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar cita
        Button(
            onClick = {
                if (newCitaMotivo.isNotBlank() && selectedMascota != null && selectedDate.isNotEmpty()) {
                    citaRepository.agregarCita(
                        Cita(
                            id = citas.size + 1,
                            mascotaId = selectedMascota!!.id,
                            fecha = selectedDate,
                            motivo = newCitaMotivo
                        )
                    )
                    newCitaMotivo = ""
                    selectedMascota = null
                    selectedDate = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Cita")
            }
        }
}
