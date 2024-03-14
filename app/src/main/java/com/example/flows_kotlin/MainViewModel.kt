package com.example.flows_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
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
        val flow1 = flow<Int> {
            emit(1)
            delay(500L)
            emit(2)
        }
        viewModelScope.launch() {
            flow1.flatMapConcat {value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect{value ->
                println("The value is $value")
            }
        }
    }
}