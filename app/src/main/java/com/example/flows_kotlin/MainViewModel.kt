package com.example.flows_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
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
        countDownFlow.onEach {
            println(it)
        }.launchIn(viewModelScope)
        
        viewModelScope.launch() {
            val reduceResult = countDownFlow
                /*.count {
                    it % 2 == 0
                }*/
                /*.reduce { accumulator, value ->
                    accumulator + value
                }*/
                .fold(100 ){ accumulator, value ->
                    accumulator + value
                }
            println("The amount of even numbers is $reduceResult")
        }
    }
}