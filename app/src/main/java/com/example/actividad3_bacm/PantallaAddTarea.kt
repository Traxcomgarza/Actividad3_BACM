package com.example.actividad3_bacm

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PantallaAddTarea(
    navController: NavHostController,
    viewModel: EventosViewModel
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("pantallaAddTarea") },
                ) {
                    Text(text = "Agregar tarea")
                }
                Button(
                    onClick = { navController.navigate("pantallaEventos") },
                ) {
                    Text(text = "Ver eventos")
                }
            }
        }

    ) { innerPadding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD8E3DF))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Eventos",
                    color = Color(0xFF008959),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
            CrearTarea(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                eventosViewModel = viewModel

            )
        }

    }

}