// Lokasi: app/src/main/java/com/example/unicase/model/Product.kt

package com.example.unicase.model

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.unicase.R

/**
 * Versi baru dari data class Product.
 */
data class Product(
    val id: Int,
    val name: String,
    val fullName: String,
    val images: List<Int>?,
    val rating: Double?,
    val reviewCount: Int?,
    val description: String?,
    val variant: String,
    val colors: List<ColorOption>?,
    val price: String,
    @DrawableRes val imageRes: Int = 0,
    val imageUri: Uri? = null
)

data class ColorOption(val name: String, val color: Color)

/**
 * Daftar produk dummy yang sudah diperbarui.
 */
val dummyProducts = listOf(
    Product(
        id = 1,
        name = "Estetikkkk",
        fullName = "Ipong 200 pro mex mex Silitcon ketupat",
        imageRes = R.drawable.gambar_ws1,
        images = listOf(R.drawable.gambar_ws1, R.drawable.gambar_ws3, R.drawable.gambar_ws2),
        rating = 4.9,
        reviewCount = 132,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        variant = "Varian: Black, Red, Mlue",
        colors = listOf(
            ColorOption("Red", Color.Red),
            ColorOption("Black", Color.Black),
        ),
        price = "Rp67.000"
    ),
    Product(
        id = 2,
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