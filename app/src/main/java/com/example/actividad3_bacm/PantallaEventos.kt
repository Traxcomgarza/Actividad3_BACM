package com.example.actividad3_bacm

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
@Composable
fun PantallaEventos(
    navController: NavHostController,
    eventosViewModel: EventosViewModel
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
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top
        ) {
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

           LazyColumn(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 16.dp),
               verticalArrangement = Arrangement.spacedBy(16.dp)
           ) {
               items(
                   eventosViewModel.eventos,
                   key = {it.titulo}
               ){
                   item ->
                   //variable eliminar para el estado
                   val eliminar = rememberSwipeToDismissBoxState()
                   if (eliminar.currentValue == SwipeToDismissBoxValue.StartToEnd ||
                       eliminar.currentValue == SwipeToDismissBoxValue.EndToStart
                   ) {//si desliza se borra con key le pasamos que elemento se borra
                       eventosViewModel.deleteEvento(item)
                   }
                   SwipeToDismissBox(
                       state = eliminar,
                       //No quise tener decoraciones solo funciones principales
                       backgroundContent = {},
                       content = {
                           //varriable booleana para saber si se expande
                           var expandir by remember { mutableStateOf(false) }

                           Card(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(8.dp)
                                   .animateContentSize(),
                               colors = CardDefaults.cardColors(
                                   containerColor = Color(0xFF1F1F1F)
                               ),
                               onClick = { expandir = !expandir }
                           ) {
                               Column(
                                   modifier = Modifier.padding(16.dp)
                               ) {
                                   Text(
                                       text = item.titulo,
                                       fontSize = 20.sp,
                                       color = Color.White
                                   )
                                //Aqui se expande y se muestra tod0
                                   if (expandir) {
                                       Spacer(modifier = Modifier.size(8.dp))
                                       Text(text = "Fecha: ${item.fecha}", color = Color.White)
                                       Text(text = "Prioridad: ${item.prioridad}", color = Color.White)
                                       Text(text = "Descripcion: ${item.descripcion}", color = Color.White)
                                   }
                               }
                           }
                       }
                   )
               }
           }
        }
    }
}