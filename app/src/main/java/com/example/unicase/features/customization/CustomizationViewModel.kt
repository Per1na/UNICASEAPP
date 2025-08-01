package com.example.unicase.features.customization // Sesuaikan dengan package Anda

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.unicase.model.LocationData
import java.util.UUID

// Data Class untuk Alamat
data class Address(
    val id: UUID = UUID.randomUUID(),
    val recipientName: String,
    val phoneNumber: String,
    val fullAddress: String,
    val label: String,
    var isSelected: Boolean = false
)

// Sealed Class untuk Layer Desain
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

    // --- State untuk Kustomisasi Case ---
    var layers = mutableStateOf<List<DesignLayer>>(emptyList())
        private set
    var selectedLayerId = mutableStateOf<UUID?>(null)
        private set
    var caseType = mutableStateOf("Hardcase")
        private set
    var printEffect = mutableStateOf("Glossy")
        private set
    var phoneBrand = mutableStateOf("Samsung")
        private set
    var phoneType = mutableStateOf("Samsung Z Flip5")
        private set
    var price = mutableStateOf(43000)
        private set

    // --- State untuk Alamat ---
    private val _addresses = mutableStateOf<List<Address>>(emptyList())
    val addresses: State<List<Address>> = _addresses

    private val _selectedAddress = mutableStateOf<Address?>(null)
    val selectedAddress: State<Address?> = _selectedAddress

    // --- State untuk Form Alamat Dinamis ---
    private val locationDataSource = LocationData()
    val provinceList: List<LocationData.Provinsi>
    private val _cityList = mutableStateOf<List<LocationData.KabupatenKota>>(emptyList())
    val cityList: State<List<LocationData.KabupatenKota>> = _cityList
    private val _districtList = mutableStateOf<List<LocationData.Kecamatan>>(emptyList())
    val districtList: State<List<LocationData.Kecamatan>> = _districtList

    init {
        // Muat daftar provinsi saat ViewModel dibuat
        provinceList = listOf(
            locationDataSource.provinsiAceh,
            locationDataSource.provinsiSumateraUtara
            // Tambahkan provinsi lain di sini
        )
        updatePrice()
    }

    // --- FUNGSI-FUNGSI KUSTOMISASI ---
    fun addImageLayer(uri: Uri) {
        val newLayer = ImageLayer(uri)
        layers.value = layers.value + newLayer
        selectedLayerId.value = newLayer.id
    }
    fun addTextLayer(text: String, color: Color) {
        if (text.isNotBlank()) {
            val newLayer = TextLayer(text, mutableStateOf(color))
            layers.value = layers.value + newLayer
            selectedLayerId.value = newLayer.id
        }
    }
    fun deleteSelectedLayer() {
        selectedLayerId.value?.let { idToDelete ->
            layers.value = layers.value.filterNot { it.id == idToDelete }
            selectedLayerId.value = layers.value.lastOrNull()?.id
        }
    }
    fun setSelectedLayerId(id: UUID?) {
        selectedLayerId.value = id
    }
    fun setCaseType(type: String) {
        caseType.value = type
        updatePrice()
    }
    fun setPrintEffect(effect: String) {
        printEffect.value = effect
        updatePrice()
    }
    fun setPhoneBrand(brand: String, defaultType: String) {
        phoneBrand.value = brand
        setPhoneType(defaultType)
    }
    fun setPhoneType(type: String) {
        phoneType.value = type
        updatePrice()
    }
    private fun updatePrice() {
        // Di sini Anda bisa menambahkan logika harga dinamis yang lebih kompleks
        price.value = 43000
    }


    // --- FUNGSI-FUNGSI ALAMAT ---
    fun addAddress(newAddress: Address) {
        val currentList = _addresses.value.toMutableList()
        if (newAddress.isSelected) {
            currentList.forEach { it.isSelected = false }
        }
        currentList.add(newAddress)
        _addresses.value = currentList
        if (newAddress.isSelected || currentList.size == 1) {
            selectAddress(newAddress.id)
        }
    }

    fun selectAddress(addressId: UUID) {
        val updatedList = _addresses.value.map { it.copy(isSelected = it.id == addressId) }
        _addresses.value = updatedList
        _selectedAddress.value = updatedList.find { it.isSelected }
    }

    // --- FUNGSI UNTUK MENGELOLA PILIHAN LOKASI ---
    fun onProvinceSelected(province: LocationData.Provinsi) {
        _cityList.value = province.daftarkabupatenKota
        _districtList.value = emptyList() // Kosongkan kecamatan saat provinsi berubah
    }

    fun onCitySelected(city: LocationData.KabupatenKota) {
        _districtList.value = city.daftarKecamatan
    }
}