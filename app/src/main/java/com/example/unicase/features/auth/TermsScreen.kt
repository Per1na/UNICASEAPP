package com.example.unicase.features.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terms & conditions") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Fungsi untuk kembali
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding dari Scaffold
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()) // Membuat konten bisa di-scroll
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Data Akun
            Text(text = "1. Data Akun", color = Color.Black, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• Informasi yang Anda berikan harus benar, lengkap, dan dapat dipertanggungjawabkan. ", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "• Anda bertanggung jawab atas keamanan akun dan kata sandi Anda. ", color = Color.Black        )
            Spacer(modifier = Modifier.height(16.dp))

            // 2. Penggunaan Layanan
            Text(text = "2. Penggunaan Layanan", color = Color.Black , style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• Akun hanya boleh digunakan oleh pemilik sah. ", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "• Dilarang menggunakan akun untuk aktivitas yang melanggar hukum atau merugikan pihak lain. ", color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))

            // 3. Konten Unggahan
            Text(text = "3. Konten Unggahan", color = Color.Black , style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• Gambar atau teks yang Anda unggah harus sesuai hukum dan etika. ", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "• Konten yang mengandung unsur SARA, pornografi, kekerasan, atau pelanggaran hak cipta akan kami tolak. ", color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))

            // 4. Kebijakan Aplikasi
            Text(text = "4. Kebijakan Aplikasi", color = Color.Black, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "• Kami berhak menonaktifkan akun yang melanggar aturan. ", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "• Syarat & ketentuan dapat berubah sewaktu-waktu. ", color = Color.Black)
            Spacer(modifier = Modifier.height(24.dp))

            // Disclaimer
            Text(text = "Dengan menekan tombol \"Setuju dan Daftar\", Anda menyatakan telah membaca dan menyetujui semua syarat & ketentuan di atas. ", color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun TermsScreenPreview() {
    UnicaseTheme {
        TermsScreen(navController = rememberNavController())
    }
}