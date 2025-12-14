package com.example.firstproject.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstproject.R
import com.example.firstproject.ui.theme.MintGreen

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardBody()

        }
    }
}
@Composable
fun CardBody(){
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MintGreen)
        ){
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Image(
                    painter = painterResource(R.drawable.abel),
                    contentDescription = null,
                    modifier = Modifier
                        .height(38.dp)
                        .width(38.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp)

            ){
                Text("Card", style = TextStyle(
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                ))

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp, vertical = 8.dp)

            ){
                Text("Simple and Easy to use app", style = TextStyle(
                    color = Color.White.copy(0.7f)

                ))

            }
            Spacer(modifier = Modifier.height(21.dp))
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 11.dp)
           ){
               MediaCard(
                   Modifier

                       .weight(1f),
                   R.drawable.image,
                   "Text"
               )
               Spacer(modifier = Modifier.width(15.dp))
               MediaCard(
                   Modifier

                       .weight(1f),
                   R.drawable.kanye,
                   "Address"
               )
           }
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth()){
                MediaCard(
                    Modifier

                        .weight(1f),
                    R.drawable.kanye,
                    "Character"
                )
                Spacer(modifier = Modifier.width(15.dp))
                MediaCard(
                    Modifier

                        .weight(1f),
                    R.drawable.kanye,
                    "Bank Card"
                )

            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth()){
                MediaCard(
                    Modifier

                        .weight(1f),
                    R.drawable.kanye,
                    "Password"
                )
                Spacer(modifier = Modifier.width(15.dp))
                MediaCard(
                    Modifier

                        .weight(1f),
                    R.drawable.kanye,
                    "Logistics"
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.outline_settings_24),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text("Settings" , style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ))
                }
            }
            
        }
    }
}
@Composable
fun MediaCard(modifier: Modifier, image : Int, label : String){
    Card(
        modifier = modifier
            .height(130.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ){
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(44.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(label, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        }
    }

}
@Preview
@Composable
fun PreviewCard(){
    CardBody()
}
