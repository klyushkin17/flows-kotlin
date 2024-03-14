package com.example.flows_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flows_kotlin.ui.theme.FlowskotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowskotlinTheme {
                val viewModel = viewModel<MainViewModel>()
                val time = viewModel.countDownFlow.collectAsState(initial = 10)
                Box(modifier = Modifier.fillMaxSize()){
                    Text(
                        text = time.value.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}