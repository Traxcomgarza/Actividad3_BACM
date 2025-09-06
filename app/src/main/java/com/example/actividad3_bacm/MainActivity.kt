package com.example.actividad3_bacm

import android.icu.text.DateFormat
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.actividad3_bacm.navigation.AppNav
import com.example.actividad3_bacm.ui.theme.Actividad3_BACMTheme
import java.time.LocalDate
import java.util.Calendar

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad3_BACMTheme {
                //para prueba
               // CrearTarea(eventosViewModel = EventosViewModel())
                val navController = rememberNavController()
                AppNav(navController = navController)
            }
        }
    }
}

data class TareasData(
    val titulo: String,
    val fecha: LocalDate,
    val prioridad: String,
    val descripcion: String
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CrearTarea(modifier: Modifier = Modifier, eventosViewModel: EventosViewModel){
    var inputTitulo by rememberSaveable { mutableStateOf("") }
    //Se inicializa en la fecha actual, se actualiza despues
    var inputFecha by rememberSaveable { mutableStateOf(LocalDate.now()) }
    //esto es para desplegar el calendario y el snackbar
    val context = LocalContext.current
    var inputPrioridad by rememberSaveable { mutableStateOf("") }
    var inputDescripcion by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        //titulo
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputTitulo,
                onValueChange = { inputTitulo = it},
                label = {Text("Introduce tu evento")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(4.dp)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF1F1F1F),
                    unfocusedIndicatorColor = Color(0xFF1F1F1F),
                    disabledIndicatorColor = Color(0xFF1F1F1F),
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White



                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))

        //Fecha
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val calendario = Calendar.getInstance()
                    android.app.DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            //Se agrega +1 porque get empieza de 0 pero localdate no
                            inputFecha = LocalDate.of(year, month + 1, day)
                        },
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                //Damos el formato para que parezca textField
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1F1F1F)
                ),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
                ) { Text(
                color = Color.White,
                text = "Seleccionar fecha: $inputFecha",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )}
        }
        Spacer(modifier = Modifier.size(10.dp))

        //prioridad
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputPrioridad,
                onValueChange = { inputPrioridad = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {Text("Introducir Prioridad (1-10)")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(4.dp)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF1F1F1F),
                    unfocusedIndicatorColor = Color(0xFF1F1F1F),
                    disabledIndicatorColor = Color(0xFF1F1F1F),
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))

        //Descripcion
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputDescripcion,
                onValueChange = { inputDescripcion = it},
                label = {Text("Introduce la descripcion")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(4.dp)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF1F1F1F),
                    unfocusedIndicatorColor = Color(0xFF1F1F1F),
                    disabledIndicatorColor = Color(0xFF1F1F1F),
                    focusedContainerColor = Color(0xFF1F1F1F),
                    unfocusedContainerColor = Color(0xFF1F1F1F),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F5AC9)),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    val validacion = eventosViewModel.addEvento(
                        inputTitulo,
                        inputFecha,
                        inputPrioridad,
                        inputDescripcion
                    )
                    if (validacion == null) {
                        Toast.makeText(context, "Tarea agregada", Toast.LENGTH_SHORT).show()
                        inputTitulo = ""
                        inputFecha = LocalDate.now()
                        inputDescripcion = ""
                        inputPrioridad = ""
                    } else {
                        Toast.makeText(context, validacion, Toast.LENGTH_SHORT).show()

                    }
                    //Muestra un mensaje de error si hay uno
                }
            ){
                Text("Agregar Evento")
            }
        }
    }
}