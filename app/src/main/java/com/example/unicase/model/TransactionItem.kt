package com.example.unicase.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R

enum class TransactionStatus(val label: String, val color: Color) {
    All("All", Color.Gray),
    Pending("Pending", Color(0xFFFFC107)),
    Shipped("Shipped", Color(0xFF4CAF50)),
    Delivered("Delivered", Color(0xFF2196F3)),
    Cancel("Cancel", Color(0xFFF44336))
}

data class TransactionItem(
    val id: Int,
    val name: String,
    val variant: String,
    val quantity: Int,
    val price: Int,
    val imageRes: Int,
    val status: TransactionStatus
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryScreen(navController: NavController) {
    var selectedStatus by remember { mutableStateOf(TransactionStatus.All) }

    val dummyTransactions = listOf(
        TransactionItem(1, "Case iPhone 13", "Custom, Black", 1, 60000, R.drawable.gambar_ws1, TransactionStatus.Pending),
        TransactionItem(2, "Case iPhone 10", "Custom", 1, 30000, R.drawable.gambar_ws2, TransactionStatus.Shipped),
        TransactionItem(3, "Case iPhone 99", "Custom", 1, 60000, R.drawable.gambar_ws3, TransactionStatus.Delivered),
        TransactionItem(4, "Case iPhone 100", "Custom", 1, 90000, R.drawable.gambar_ws1, TransactionStatus.Cancel)
    )

    val filteredTransactions = if (selectedStatus == TransactionStatus.All) dummyTransactions
    else dummyTransactions.filter { it.status == selectedStatus }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Transaction History", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransactionStatus.entries.forEach { status ->
                    FilterChip(
                        selected = selectedStatus == status,
                        onClick = { selectedStatus = status },
                        label = { Text(status.label, fontSize = 8.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = status.color.copy(alpha = 0.2f),
                            selectedLabelColor = status.color,
                            containerColor = Color.LightGray.copy(alpha = 0.2f),
                            labelColor = Color.Black
                        )
                    )
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredTransactions) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = item.imageRes,
                                contentDescription = item.name,
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.name, fontWeight = FontWeight.Bold)
                                Text(item.variant, fontSize = 12.sp, color = Color.Gray)
                                Text("${item.quantity} item", fontSize = 12.sp)
                                Text("Rp ${item.price}", fontWeight = FontWeight.SemiBold)
                            }
                            Box(
                                modifier = Modifier
                                    .background(item.status.color.copy(alpha = 0.2f), shape = RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(item.status.label, color = item.status.color, fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TransactionHistoryScreenPreview() {
    TransactionHistoryScreen(navController = rememberNavController())
}