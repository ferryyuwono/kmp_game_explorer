package com.ferry.gameexplorer.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferry.gameexplorer.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen(viewModel: MainViewModel = MainViewModel()) {
    val list = viewModel.gameList.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(weight = 1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                    ),
                    onClick = {
                        viewModel.load()
                    }
                ) {
                    Text(
                        text = "Load",
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier.weight(weight = 1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                    ),
                    onClick = {
                        viewModel.reset()
                    }
                ) {
                    Text(
                        text = "Reset",
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        items(list.value) {
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF,
    showSystemUi = true,
)
@Composable
fun GameScreenPreview() {
    MyApplicationTheme {
        GameScreen()
    }
}
