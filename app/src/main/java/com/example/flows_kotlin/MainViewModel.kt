package com.example.flows_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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
        countDownFlow.onEach {
            println(it)
        }.launchIn(viewModelScope)
        
        viewModelScope.launch() {
            countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time + time
                }
                .onEach { time ->
                    println(time)
                }
                .collect { time ->
                    println("The current time is $time")
                }
        }
    }
}