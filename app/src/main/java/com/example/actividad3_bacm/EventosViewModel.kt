package com.example.actividad3_bacm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class EventosViewModel: ViewModel() {

    val eventos = mutableStateListOf<TareasData>()

    fun addEvento(
        titulo: String,
        fecha: LocalDate,
        prioridad: String,
        descripcion: String
    ): String? {
        if (titulo.isBlank()) {
            return "El título no puede estar vacío"
        }

        val prioridadNum = prioridad.toIntOrNull()
        //si es nulo o no esta dentro del 1-10
        if (prioridadNum == null || prioridadNum !in 1..10) {
            return "La prioridad debe ser un número entre 1 y 10"
        }

        if (descripcion.isBlank()) {
            return "La descripción no puede estar vacía"
        }

        val nuevoEvento = TareasData(
            titulo = titulo,
            fecha = fecha,
            prioridad = prioridad,
            descripcion = descripcion
        )
        eventos.add(nuevoEvento)
        return null //significa que no hay errores
    }

    fun deleteEvento(evento: TareasData) {
        eventos.remove(evento)
    }
}