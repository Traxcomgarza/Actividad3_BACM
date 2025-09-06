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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
        containerColor = Color(0xFF111111),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("pantallaAddTarea") },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5F5AC9),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(4.dp)

                ) {
                    Text(text = "Agregar tarea")
                }
                Button(
                    onClick = { navController.navigate("pantallaEventos") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5F5AC9),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(4.dp)
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
                    .padding(16.dp)
            ) {
                Text(
                    text = "Eventos",
                    color = Color.White,
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