package com.example.firstproject.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstproject.R

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileBody()

            }
        }
    }
@Composable
fun ProfileBody() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = null
                )
                Text("username", style = TextStyle(
                    fontWeight = FontWeight.Bold
                ))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                    contentDescription = null
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)

                        .clip(shape = CircleShape),

                    contentScale = ContentScale.Crop


                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("67", style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ))
                    Text("Posts", style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("901K", style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ))
                    Text("Followers",style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ))

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("67", style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ))
                    Text("Following" , style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ))

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ){
                Text("Username" ,
                    style = TextStyle( fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                ))
                Text("GGGGGGGGGGGGGG",
                    style = TextStyle(
                        fontSize = 13.sp
                ))
                Text("Followed by username" , style = TextStyle(
                    fontWeight = FontWeight.Bold
                ))

            }

            OutlinedButton(onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Button", style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ))
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,

            ){
                Button(
                    onClick ={

                    }, modifier = Modifier.padding(),
                    shape = RoundedCornerShape(8.dp),


                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Blue
                    )){
                    Text("Follow")
                }
                Button(
                    onClick ={

                    }, modifier = Modifier.padding(),
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(.5.dp, Color.Black),

                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )){
                    Text("Message")
                }
                Button(
                    onClick ={

                    }, modifier = Modifier.padding(),
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(.5.dp, Color.Black),

                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )){
                    Text("Email")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )


            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text("Story 1")
                Text("Story 2")
                Text("Story 3")
                Text("Story 4")
            }





        }
    }
}


@Preview
@Composable
fun PreviewProfile(){
    ProfileBody()
}