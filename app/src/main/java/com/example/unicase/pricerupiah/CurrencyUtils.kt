package com.example.unicase.pricerupiah

import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(price: Number): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    formatter.maximumFractionDigits = 0 // menghilangkan angka 0
    val result = formatter.format(price.toDouble())
    return result.replace("Rp", "Rp")
}

