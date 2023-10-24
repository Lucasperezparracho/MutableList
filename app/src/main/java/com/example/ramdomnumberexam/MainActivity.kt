package com.example.ramdomnumberexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.ramdomnumberexam.ui.theme.RamdomNumberExamTheme

class MainActivity : ComponentActivity() {
    // Obtén una instancia del ViewModel
    private val viewModel: RandomNumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RamdomNumberExamTheme {
                // Pasa el ViewModel al composable
                RandomNumberUI(viewModel)
            }
        }
    }
}
@Composable
fun RandomNumberUI(viewModel: RandomNumberViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = viewModel.text,
            onValueChange = { newText ->
                viewModel.text = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.generateRandomNumber()
                viewModel.addRandomNumber() // Agregar un número aleatorio a la lista
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Generar Número Aleatorio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Número aleatorio: ${viewModel.randomNumber}",
            style = MaterialTheme.typography.bodyMedium
        )

        // Mostrar la lista de números aleatorios generados
        Text(
            text = "Números aleatorios: ${viewModel.numberList.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
