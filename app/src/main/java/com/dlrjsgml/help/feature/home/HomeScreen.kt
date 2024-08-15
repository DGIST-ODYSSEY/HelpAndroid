package com.dlrjsgml.help.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),
    navBottomVisible: (Boolean) -> Unit,
    ){
    LaunchedEffect(key1 = true) {
        navBottomVisible(true)
    }

}