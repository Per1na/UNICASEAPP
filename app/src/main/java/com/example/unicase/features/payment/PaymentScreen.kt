package com.example.unicase.features.payment

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.ui.theme.UnicaseTheme
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    totalAmount: Int,
    vaNumber: String = "1234567890"
) {
    var isPaymentConfirmed by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            // Tampilan Pembayaran (Awal)
            AnimatedVisibility(
                visible = !isPaymentConfirmed,
                exit = fadeOut(animationSpec = tween(durationMillis = 200)) // Hilang dengan cepat
            ) {
                PaymentContent(
                    totalAmount = totalAmount,
                    vaNumber = vaNumber,
                    onConfirmClick = { isPaymentConfirmed = true },
                    onDetailClick = { /* TODO */ }
                )
            }

            // Tampilan Konfirmasi (Setelah Tombol Ditekan)
            if (isPaymentConfirmed) {
                ConfirmationContent(
                    isConfirmed = isPaymentConfirmed,
                    onGoToMyOrderClick = { /* TODO: Navigasi ke halaman My Order */ }
                )
            }
        }
    }
}

@Composable
private fun PaymentContent(
    totalAmount: Int,
    vaNumber: String,
    onConfirmClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    val clipboard = LocalClipboardManager.current
    val formattedTotalAmount = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalAmount)
        .replace("Rp", "Rp ")
        .replace(",00", "")

    var timeLeftInSeconds by remember { mutableStateOf(899) }
    var proofImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> proofImageUri = uri }
    )

    LaunchedEffect(key1 = timeLeftInSeconds) {
        if (timeLeftInSeconds > 0) {
            delay(1000L)
            timeLeftInSeconds--
        }
    }

    val minutes = timeLeftInSeconds / 60
    val seconds = timeLeftInSeconds % 60
    val timerText = String.format("%02d:%02d", minutes, seconds)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Manual Transfer", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Please transfer before in", fontSize = 12.sp, color = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF2885F0))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                ) {
                    Text(timerText, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.logo_bri), contentDescription = "Bank Logo", modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Bank BRI", fontWeight = FontWeight.Bold, color = Color.Black)
                        Text("PT. Unicase", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
            InfoRowWithCopy(value = vaNumber, onCopyClick = { clipboard.setText(AnnotatedString(vaNumber)) })
            InfoRowWithCopy(label = "Nominal Transfer", value = formattedTotalAmount, onCopyClick = { clipboard.setText(AnnotatedString(totalAmount.toString())) })
            Text("Upload Proof Payment", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
            UploadBox(selectedUri = proofImageUri, onUploadClick = { galleryLauncher.launch("image/*") })
            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = onDetailClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Detail Transaction", fontSize = 16.sp, color = Color.Black)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.ChevronRight, contentDescription = "Go to detail", tint = Color.Black)
            }
            Spacer(modifier = Modifier.height(80.dp)) // Spacer untuk tombol di bawah
        }

        Button(
            onClick = onConfirmClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2885F0)),
            enabled = proofImageUri != null
        ) {
            Text(
                text = "I already transfer",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun ConfirmationContent(isConfirmed: Boolean, onGoToMyOrderClick: () -> Unit) {
    var showDetails by remember { mutableStateOf(false) }
    LaunchedEffect(isConfirmed) {
        if (isConfirmed) {
            delay(600) // Tunda kemunculan teks dan tombol
            showDetails = true
        }
    }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Menganimasikan skala dari 30x (memenuhi layar) ke 1x (ukuran normal)
    val scale by animateFloatAsState(
        targetValue = if (isConfirmed) 1f else 30f,
        animationSpec = tween(durationMillis = 600),
        label = "scaleAnimation"
    )
    // Menganimasikan posisi vertikal dari tengah ke sedikit ke atas
    val offsetY by animateDpAsState(
        targetValue = if (isConfirmed) -(screenHeight / 5) else 0.dp,
        animationSpec = tween(durationMillis = 600),
        label = "offsetAnimation"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Box yang dianimasikan
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationY = offsetY.value
                )
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFBEDAFA)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "Payment Complete",
                tint = Color(0xFF2885f0),
                modifier = Modifier.size(60.dp)
            )
        }

        // Teks dan tombol yang muncul setelah animasi selesai
        AnimatedVisibility(visible = showDetails, enter = fadeIn(animationSpec = tween(delayMillis = 200))) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 24.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(
                    "Upload Payment Complete",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Your ID Booking has been sent to your phone number and email. You can check your payment status.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onGoToMyOrderClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2885F0)), // Diubah ke warna biru
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Go to My Order", color = Color.White)
                }
            }
        }
    }
}


@Composable
private fun InfoRowWithCopy(label: String? = null, value: String, onCopyClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        label?.let {
            Text(it, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFF0F0F0)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Default.ContentCopy,
                    contentDescription = "Copy",
                    modifier = Modifier.size(24.dp).clickable(onClick = onCopyClick),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun UploadBox(selectedUri: Uri?, onUploadClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable(onClick = onUploadClick),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF0F0F0)
    ) {
        if (selectedUri != null) {
            AsyncImage(
                model = selectedUri,
                contentDescription = "Proof Preview",
                modifier = Modifier.fillMaxSize().padding(8.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.FileUpload, contentDescription = "Upload", modifier = Modifier.size(40.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Upload here", color = Color.Gray)
                Text("Max: 3MB", color = Color.Gray, fontSize = 12.sp)
                Text("Format: jpg, jpeg, png", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PaymentScreenPreview() {
    val fakeNavController = rememberNavController()
    UnicaseTheme {
        PaymentScreen(
            navController = fakeNavController,
            totalAmount = 103000
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ConfirmationScreenPreview() {
    UnicaseTheme {
        ConfirmationContent(isConfirmed = true, onGoToMyOrderClick = {})
    }
}