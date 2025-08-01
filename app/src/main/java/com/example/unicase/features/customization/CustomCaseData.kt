package com.example.unicase.features.customization

import android.net.Uri
import java.io.Serializable

data class CustomCaseData(
    val imageUri: Uri? = null,
    val text: String? = null,
    val phoneType: String = "",
    val price: Int = 0
) : Serializable


