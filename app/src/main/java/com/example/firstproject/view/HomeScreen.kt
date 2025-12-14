package com.example.firstproject.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstproject.R


@Composable
fun HomeScreen() {
    val categories = listOf(
        R.drawable.image,
        R.drawable.kanye,
        R.drawable.image,
        R.drawable.image,
        R.drawable.image,


        )
    val categoryName = listOf(
        "Beauty",
        "Home & Decor",
        "Fashion",
        "Appliances",
        "Party Items"
    )
    val flashImage = listOf(
        R.drawable.image,      // replace with actual images
        R.drawable.kanye,
        R.drawable.image
    )
    val flashTitle = listOf(
        "Sonic Headphones",
        "Mini Clock",
        "Wireless Earpods"

    )
    var search by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Search Products...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = Color.LightGray
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Spacer(Modifier.height(16.dp))
        }
        item {
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            LazyRow {
                items(categories.size) { index ->
                    Column(
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Image(
                            painter = painterResource(categories[index]),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Text(categoryName[index])
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Flash Sale",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            FlashSaleGrid(flashImage,flashTitle)
            Spacer(modifier = Modifier.height(20.dp))


        }

    }
}
@Composable
fun FlashSaleGrid(
    flashImage: List<Int>,
    flashTitle: List<String>
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(flashImage.size) { index ->
            FlashSaleCard(
                image = flashImage[index],
                title = flashTitle[index],
                price = "PKR 800",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Composable
fun FlashSaleCard(
    modifier: Modifier,
    image: Int,
    title: String,
    price: String
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(210.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = price,
                fontSize = 14.sp,
                color = Color(0xFFE53935),
                fontWeight = FontWeight.Bold
            )
        }
    }
}


