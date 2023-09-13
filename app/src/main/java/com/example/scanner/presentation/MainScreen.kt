package com.example.scanner.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = state.value.detail, fontWeight = FontWeight.SemiBold)

        Button(onClick = viewModel::startScan) {
            Text(text = "Scan")
        }
    }

}