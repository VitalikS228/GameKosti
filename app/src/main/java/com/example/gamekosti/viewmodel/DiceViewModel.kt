package com.example.gamekosti.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    private val _diceState = MutableStateFlow(List(5) { 1 })
    val diceState: StateFlow<List<Int>> get() = _diceState

    private val _isRolling = MutableStateFlow(false)
    val isRolling: StateFlow<Boolean> get() = _isRolling

    private var rollJob: Job? = null

    fun startRolling() {
        if (_isRolling.value) return
        _isRolling.value = true

        rollJob = CoroutineScope(Dispatchers.Default).launch {
            while (_isRolling.value) {
                _diceState.value = List(5) { Random.nextInt(1, 7) }
                kotlinx.coroutines.delay(300)
            }
        }
    }

    fun stopRolling() {
        _isRolling.value = false
        rollJob?.cancel()
    }
}
