package com.example.unicase.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.R
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // --- BAGIAN GAMBAR ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Gambar besar kiri
                Box(
                    modifier = Modifier
                        .weight(1.3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(18.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gambar_ws1),
                        contentDescription = "Welcome Illustration",
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(1.4f) // Perbesar aman
                            .offset(x =(-8).dp, y = (-5).dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Dua gambar kanan atas & bawah
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Gambar kanan atas
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(184.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gambar_ws2),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(1.5f)
                                .offset(x =(0).dp, y = (20).dp)
                        )
                    }

                    // Gambar kanan bawah
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(184.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gambar_ws3),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(1.7f)
                                .offset(x =(0).dp, y = (30).dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.2f))

        // Teks sambutan
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Welcome to",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp


            )
            UnicaseLogoText()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Phone Case, Your Style!\nDesign it yourself or choose from our\nawesome collection.",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol dan Sign In
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Button(
                onClick = { navController.navigate("signup") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(text = "Let Get Started",
                    color = Color.White,
                    fontSize = 16.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Already have an account?",
                    modifier = Modifier,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray)

                TextButton(onClick = { navController.navigate("signin") }) {
                    Text(text = "Sign in",
                        color = PrimaryBlue,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun UnicaseLogoText() {
    Row {
        Text(
            text = "UNI",
            color = PrimaryBlue,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Text(
            text = "CASE",
            color = Color.Black,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun WelcomeScreenPreview() {
    UnicaseTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}
