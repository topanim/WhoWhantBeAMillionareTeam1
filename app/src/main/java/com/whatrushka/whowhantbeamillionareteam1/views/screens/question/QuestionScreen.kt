package com.whatrushka.whowhantbeamillionareteam1.views.screens.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Preview(showBackground = true)
@Composable
fun QuestionScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

            ){
            Button(onClick = { /*TODO*/ }) {

            }

            Text(text = "valueOfQuestion")

            Button(onClick = { /*TODO*/ }) {

            }
        }
        Spacer(modifier = Modifier.height(48.dp))

        Text(text = "time")
        Spacer(modifier = Modifier.height(48.dp))

        Text(text = "quastion")

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { /*TODO*/ }) {
            
        }
        Button(onClick = { /*TODO*/ }) {

        }
        Button(onClick = { /*TODO*/ }) {

        }
        Button(onClick = { /*TODO*/ }) {

        }

        Spacer(modifier = Modifier.height(48.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

        ){
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }

    //закомитеть в дев
    //заверджить со своей веткой
    //
}