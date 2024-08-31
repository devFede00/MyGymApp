package com.example.mygym.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mygym.R

import com.example.mygym.ui.theme.MyGymTheme


@Composable
fun StartScreen(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFBB86FC).copy(alpha = 0.3f)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp,start = 40.dp, end = 40.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Benvenuto in",
                    fontSize = 36.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "MyGym",
                    fontSize = 36.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_list_alt_24),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Button(onClick = {

        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Iniziamo!",
                fontSize = 28.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }
    }
}

@Preview(showBackground = true, )
@Composable
fun StartScreenPreview() {
    MyGymTheme {
        StartScreen()
    }
}