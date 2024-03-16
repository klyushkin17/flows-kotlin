package com.example.flows_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
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

    /*val countDownFlow = flow<Int> {
        val staringValue = 10
        var currentValue = staringValue
        emit(currentValue)
        while (currentValue > 0)
        {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }*/

    /*private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()*/

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init{
        //collectFlow()
        viewModelScope.launch {
            sharedFlow.collect{
                delay(2000L)
                println("FIRST FLOW: The received number is $it")
            }
        }

        viewModelScope.launch {
            sharedFlow.collect{
                delay(3000L)
                println("SECOND FLOW: The received number is $it")
            }
        }

        sqareNumber(3)
    }

    fun sqareNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    /*fun incrementCounter() {
        _stateFlow.value += 1
    }
*/
    /*private fun collectFlow(){
        val flow = flow {
            delay(1000L)
            emit("Appetizer")
            delay(2000L)
            emit("Main dish")
            delay(500L)
            emit("Dessert")
        }
        viewModelScope.launch {
            flow.onEach{
                println("$it is delivered")
            }
                //.buffer()
                //.conflate()
                .collect {
                    println("Now eating $it")
                    delay(2000L)
                    println("Finished to eating $it")
                }
        }
    }*/
}