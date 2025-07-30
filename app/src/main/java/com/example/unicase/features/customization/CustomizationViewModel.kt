package com.example.unicase.features.customization

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import java.util.UUID

// Model data yang sama dari CustomCaseScreen
sealed class DesignLayer(
    val id: UUID = UUID.randomUUID(),
    var scale: MutableState<Float> = mutableStateOf(1f),
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var rotation: MutableState<Float> = mutableStateOf(0f)
)
data class ImageLayer(val uri: Uri) : DesignLayer()
data class TextLayer(var text: String, var color: MutableState<Color>) : DesignLayer()


class CustomizationViewModel : ViewModel() {
    // State untuk semua layer desain
    var layers = mutableStateOf<List<DesignLayer>>(emptyList())
        private set

    // State untuk layer yang sedang dipilih
    var selectedLayerId = mutableStateOf<UUID?>(null)
        private set

    // State untuk opsi lain
    var caseType = mutableStateOf("Hardcase")
        private set
    var printEffect = mutableStateOf("Glossy")
        private set
    var phoneBrand = mutableStateOf("Samsung")
        private set
    var phoneType = mutableStateOf("Samsung Z Flip5")
        private set
    var price = mutableStateOf(43000) // Harga dasar + efek, bisa dibuat lebih dinamis
        private set


    // --- Fungsi untuk memanipulasi state dari UI ---

    fun setLayers(newLayers: List<DesignLayer>) {
        layers.value = newLayers
    }

    fun setSelectedLayerId(id: UUID?) {
        selectedLayerId.value = id
    }

    fun deleteSelectedLayer() {
        if (selectedLayerId.value != null) {
            layers.value = layers.value.filterNot { it.id == selectedLayerId.value }
            selectedLayerId.value = layers.value.lastOrNull()?.id
        }
    }

    fun addImageLayer(uri: Uri) {
        val newImageLayer = ImageLayer(uri)
        val existingImageIndex = layers.value.indexOfFirst { it is ImageLayer }

        val newLayers = layers.value.toMutableList()
        if (existingImageIndex != -1) {
            // Ganti gambar yang ada
            newLayers[existingImageIndex] = newImageLayer
        } else {
            // Tambah gambar baru
            newLayers.add(newImageLayer)
        }
        layers.value = newLayers
        selectedLayerId.value = newImageLayer.id
    }

    fun addTextLayer(text: String, color: Color) {
        if (text.isNotEmpty()) {
            val newLayer = TextLayer(text, mutableStateOf(color))
            layers.value = layers.value + newLayer
            selectedLayerId.value = newLayer.id
        }
    }

    fun setPhoneBrand(brand: String, defaultType: String) {
        phoneBrand.value = brand
        phoneType.value = defaultType
    }

    fun setPhoneType(type: String) {
        phoneType.value = type
    }

    // Fungsi lain bisa ditambahkan sesuai kebutuhan
}