package com.example.actividad3_bacm.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.actividad3_bacm.EventosViewModel
import com.example.actividad3_bacm.PantallaAddTarea
import com.example.actividad3_bacm.PantallaEventos

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNav(
    navController: NavHostController
) {
    // Instancia del ViewModel
    val eventosViewModel: EventosViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "pantallaAddTarea"
    ) {
        composable("pantallaAddTarea") {PantallaAddTarea(navController, eventosViewModel)}
        composable("pantallaEventos") {PantallaEventos(navController, eventosViewModel)}
        }
    }


annotation class AppNav