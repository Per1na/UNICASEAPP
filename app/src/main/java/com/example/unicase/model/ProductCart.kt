package com.example.unicase.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.unicase.pricerupiah.formatRupiah
import com.example.unicase.repository.ProductResponse
import com.example.unicase.ui.theme.LightGray
import com.example.unicase.ui.theme.PrimaryBlue

@Composable
fun ProductCard(product: ProductResponse, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.clickable(onClick = onClick),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.White),
        border = BorderStroke(2.dp, LightGray)
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.name ?:"", fontWeight = FontWeight.Bold, maxLines = 1, color = Black)
                Text(product.description ?:"", fontSize = 12.sp, color = Color.Gray, maxLines = 1)
                Text(text = formatRupiah(product.price ?: 0), fontWeight = FontWeight.Bold, color = PrimaryBlue)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(14.dp)
                    )
                    Text("4.5", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray) // dummy rating
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}
