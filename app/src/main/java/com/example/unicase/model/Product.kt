package com.example.unicase.model

import androidx.compose.ui.graphics.Color
import com.example.unicase.R

/**
 * Ini adalah satu-satunya definisi (single source of truth) untuk data produk.
 * Semua layar yang menampilkan produk akan menggunakan data class ini.
 */
data class Product(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageRes: Int,
    val images: List<Int>, // <-- INI YANG PERLU DITAMBAHKAN
    val rating: Double,
    val reviewCount: Int,
    val description: String,
    val variant: String,
    val colors: List<ColorOption>,
    val price: String
)

data class ColorOption(val name: String, val color: Color)

/**
 * Daftar produk dummy yang bisa diakses dari mana saja dalam aplikasi.
 */
val dummyProducts = listOf(
    Product(
        id = 1,
        name = "Estetikkkk",
        fullName = "Ipong 200 pro mex mex Silitcon ketupat",
        imageRes = R.drawable.gambar_ws1,
        images = listOf( // <-- TAMBAHKAN LIST GAMBAR INI
            R.drawable.gambar_ws1,
            R.drawable.gambar_ws3,
            R.drawable.gambar_ws2
        ),
        rating = 4.9,
        reviewCount = 132,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur blandit, risus nec vehicula ultrices, magna lacus bibendum urna, eget commodo tortor felis ac felis. Suspendisse potenti.",
        variant = "Varian: Black, Red, Mlue",
        colors = listOf(
            ColorOption("Red", Color.Red),
            ColorOption("Black", Color.Black),
            ColorOption("Blue", Color.Blue),
            ColorOption("Green", Color.Green)),
        price = "Rp67.000"
    ),
    Product(
        id = 2,
        name = "Silikont Ipong 20 MEK",
        fullName = "Silikon Ipong 20 MEK",
        imageRes = R.drawable.gambar_ws1,
        images = listOf(R.drawable.gambar_ws1), // <-- TAMBAHKAN JUGA DI SINI
        rating = 4.9,
        reviewCount = 99,
        description = "Deskripsi untuk Silikont Ipong 20 MEK.",
        variant = "Varian: Black, Red, Mlue",
        colors = listOf(ColorOption("Black", Color.Black)),
        price = "Rp. 80.000"
    ),

    Product(
        id = 3,
        name = "Silikont Ipong 20 MEK",
        fullName = "Silikon Ipong 20 MEK",
        imageRes = R.drawable.gambar_ws1,
        images = listOf(R.drawable.gambar_ws1),
        rating = 4.9,
        reviewCount = 99,
        description = "Deskripsi untuk Silikont Ipong 20 MEK.",
        variant = "Varian: Black, Red, Mlue",
        colors = listOf(ColorOption("Black", Color.Black)),
        price = "Rp. 80.000"
    ),

    Product(
    id = 4,
    name = "Silikont Ipong 20 MEK",
    fullName = "Silikon Ipong 20 MEK",
    imageRes = R.drawable.gambar_ws1,
    images = listOf(R.drawable.gambar_ws1),
    rating = 4.9,
    reviewCount = 99,
    description = "Deskripsi untuk Silikont Ipong 20 MEK.",
    variant = "Varian: Black, Red, Mlue",
    colors = listOf(ColorOption("Black", Color.Black)),
    price = "Rp. 80.000"
    ),

    Product(
        id = 5,
        name = "Silikont Ipong 20 MEK",
        fullName = "Silikon Ipong 20 MEK",
        imageRes = R.drawable.gambar_ws1,
        images = listOf(R.drawable.gambar_ws1),
        rating = 4.9,
        reviewCount = 99,
        description = "Deskripsi untuk Silikont Ipong 20 MEK.",
        variant = "Varian: Black, Red, Mlue",
        colors = listOf(ColorOption("Black", Color.Black)),
        price = "Rp. 80.000"
    ),

    Product(
        id = 6,
        name = "Silikont Ipong 20 MEK",
        fullName = "Silikon Ipong 20 MEK",
        imageRes = R.drawable.gambar_ws1,
        images = listOf(R.drawable.gambar_ws1),
        rating = 4.9,
        reviewCount = 99,
        description = "Deskripsi untuk Silikont Ipong 20 MEK.",
        variant = "Varian: Black, Red, Mlue",
        colors = listOf(ColorOption("Black", Color.Black)),
        price = "Rp. 80.000"
    )
)