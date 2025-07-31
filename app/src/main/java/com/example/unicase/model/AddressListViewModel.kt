package com.example.unicase.model

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize

/**
 * Data class untuk merepresentasikan satu alamat yang sudah jadi.
 * @Parcelize memungkinkan objek ini dikirim antar layar jika diperlukan.
 */
@Parcelize
data class Address(
    val recipientName: String,
    val phone: String,
    val province: String,
    val city: String,
    val district: String,
    val postalCode: String,
    val streetAddress: String,
    val isMain: Boolean = false
) : Parcelable

/**
 * ViewModel ini bertugas menyimpan daftar alamat secara global (shared).
 */
class AddressListViewModel : ViewModel() {

    // State untuk menyimpan list alamat yang sudah ditambahkan.
    private val _addresses = MutableStateFlow<List<Address>>(emptyList())
    val addresses = _addresses.asStateFlow()

    // State untuk menyimpan alamat yang dipilih (digunakan di Checkout).
    private val _selectedAddress = MutableStateFlow<Address?>(null)
    val selectedAddress = _selectedAddress.asStateFlow()

    /**
     * Fungsi untuk menambahkan alamat baru ke dalam daftar.
     */
    fun addAddress(newAddress: Address) {
        _addresses.update { currentList ->
            currentList + newAddress
        }
    }

    /**
     * Fungsi untuk memilih salah satu alamat.
     */
    fun selectAddress(address: Address) {
        _selectedAddress.value = address
    }
}
