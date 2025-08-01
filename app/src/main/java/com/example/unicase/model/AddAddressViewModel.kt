package com.example.unicase.model

import androidx.lifecycle.ViewModel
import com.example.unicase.model.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * State untuk form tambah alamat.
 */
data class AddAddressUiState(
    val name: String = "",
    val phone: String = "",
    val streetAddress: String = "",
    val isMainAddress: Boolean = false,
    val provinceList: List<LocationData.Provinsi> = emptyList(),
    val selectedProvince: LocationData.Provinsi? = null,
    val cityList: List<LocationData.KabupatenKota> = emptyList(),
    val selectedCity: LocationData.KabupatenKota? = null,
    val districtList: List<LocationData.Kecamatan> = emptyList(),
    val selectedDistrict: LocationData.Kecamatan? = null,
    val postalCode: String = ""
)

/**
 * ViewModel untuk mengelola logika dan state dari AddAddressScreen.
 */
class AddAddressViewModel : ViewModel() {

    private val locationDataSource = LocationData()

    private val _uiState = MutableStateFlow(AddAddressUiState())
    val uiState = _uiState.asStateFlow()

    private val _selectedAddress = MutableStateFlow<Address?>(null)
    val selectedAddress: StateFlow<Address?> = _selectedAddress

    fun selectAddress(address: Address) {
        _selectedAddress.value = address
    }

    init {
        loadProvinces()
    }

    private fun loadProvinces() {
        val allProvinces = listOf(
            locationDataSource.provinsiAceh,
            locationDataSource.provinsiSumateraUtara
        )
        _uiState.update { it.copy(provinceList = allProvinces) }
    }

    fun onProvinceSelected(province: LocationData.Provinsi) {
        _uiState.update {
            it.copy(
                selectedProvince = province,
                cityList = province.daftarkabupatenKota,
                selectedCity = null,
                districtList = emptyList(),
                selectedDistrict = null,
                postalCode = ""
            )
        }
    }

    fun onCitySelected(city: LocationData.KabupatenKota) {
        _uiState.update {
            it.copy(
                selectedCity = city,
                districtList = city.daftarKecamatan,
                selectedDistrict = null,
                postalCode = ""
            )
        }
    }

    fun onDistrictSelected(district: LocationData.Kecamatan) {
        _uiState.update {
            it.copy(
                selectedDistrict = district,
                postalCode = district.daftarKodePos.firstOrNull() ?: ""
            )
        }
    }

    /**
     * Fungsi ini dipanggil saat tombol Simpan ditekan.
     * Ia akan membuat objek Address dan menambahkannya ke AddressListViewModel.
     */
    fun onSaveAddress(addressListViewModel: AddressListViewModel) {
        val currentState = _uiState.value
        if (currentState.name.isNotBlank() && currentState.selectedProvince != null) {
            val newAddress = Address(
                recipientName = currentState.name,
                phone = currentState.phone,
                province = currentState.selectedProvince.nama,
                city = currentState.selectedCity?.nama ?: "",
                district = currentState.selectedDistrict?.nama ?: "",
                postalCode = currentState.postalCode,
                streetAddress = currentState.streetAddress,
                isMain = currentState.isMainAddress
            )
            addressListViewModel.addAddress(newAddress)
        }
    }

    fun onNameChange(newName: String) { _uiState.update { it.copy(name = newName) } }
    fun onPhoneChange(newPhone: String) { _uiState.update { it.copy(phone = newPhone) } }
    fun onStreetAddressChange(newAddress: String) { _uiState.update { it.copy(streetAddress = newAddress) } }
    fun onSetMainAddress(isMain: Boolean) { _uiState.update { it.copy(isMainAddress = isMain) } }
}