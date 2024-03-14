package com.example.flows_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val countDownFlow = flow<Int> {
        val staringValue = 10
        var currentValue = staringValue
        emit(currentValue)
        while (currentValue > 0)
        {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init{
        collectFlow()
    }

    private fun collectFlow(){
        viewModelScope.launch() {
            countDownFlow.collectLatest { time ->
                println("The current time is $time")
            }
        }
    }
}