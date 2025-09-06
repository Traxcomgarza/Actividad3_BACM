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
    ) {//se junta tod0 para hacer solo 1 llamada
        val nuevoEvento = TareasData(
            titulo = titulo,
            fecha = fecha,
            prioridad = prioridad,
            descripcion = descripcion
        )
        eventos.add(nuevoEvento)
    }
    //revisar
    fun deleteEvento(evento:TareasData){
        eventos.remove(evento)

    }
}