package com.example.gamekosti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamekosti.viewmodel.DiceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceGameScreen()
        }
    }
}

@Composable
fun DiceGameScreen(viewModel: DiceViewModel = viewModel()) {
    val diceState by viewModel.diceState.collectAsState()
    val isRolling by viewModel.isRolling.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(5) { index ->
                DiceImage(diceState[index])
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.startRolling() },
                enabled = !isRolling
            ) {
                Text(text = "Roll")
            }
            Button(
                onClick = { viewModel.stopRolling() },
                enabled = isRolling
            ) {
                Text(text = "Stop")
            }
        }
    }
}

@Composable
fun DiceImage(diceValue: Int) {
    val diceResource = when (diceValue) {
        1 -> R.drawable.one
        2 -> R.drawable.two
        3 -> R.drawable.three
        4 -> R.drawable.four
        5 -> R.drawable.five
        6 -> R.drawable.six
        else -> R.drawable.one
    }
    Image(
        painter = painterResource(id = diceResource),
        contentDescription = "Dice $diceValue",
        modifier = Modifier.size(64.dp)
    )
}
