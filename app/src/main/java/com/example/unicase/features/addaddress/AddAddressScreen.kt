package com.example.unicase.features.addaddress

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unicase.model.AddAddressUiState
import com.example.unicase.model.AddAddressViewModel
import com.example.unicase.model.Address
import com.example.unicase.model.AddressListViewModel
import com.example.unicase.model.LocationData
import com.example.unicase.ui.theme.UnicaseTheme

/**
 * 1. STATEFUL WRAPPER (Composable Utama)
 * Composable ini bertugas menghubungkan ViewModel dengan UI.
 * Ini adalah yang Anda panggil dari AppNavigation.
 */
@Composable
fun AddAddressScreen(
    addAddressViewModel: AddAddressViewModel = viewModel(),
    addressListViewModel: AddressListViewModel,
    onBackClick: () -> Unit,
    onAddressSelected: (Address) -> Unit

) {
    val uiState by addAddressViewModel.uiState.collectAsState()

    AddAddressContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onNameChange = addAddressViewModel::onNameChange,
        onPhoneChange = addAddressViewModel::onPhoneChange,
        onProvinceSelected = { index ->
            addAddressViewModel.onProvinceSelected(uiState.provinceList[index])
        },
        onCitySelected = { index ->
            addAddressViewModel.onCitySelected(uiState.cityList[index])
        },
        onDistrictSelected = { index ->
            addAddressViewModel.onDistrictSelected(uiState.districtList[index])
        },
        onStreetAddressChange = addAddressViewModel::onStreetAddressChange,
        onSetMainAddress = addAddressViewModel::onSetMainAddress,

        // --- PERBAIKAN DI SINI ---
        onSaveAddress = {
            // Panggil fungsi ViewModel dengan parameter yang dibutuhkan
            addAddressViewModel.onSaveAddress(addressListViewModel)
            // Panggil onBackClick untuk kembali ke layar sebelumnya
            onBackClick()
        }
    )
}

/**
 * 2. STATELESS UI (Hanya Tampilan)
 * Composable ini berisi semua elemen UI dan tidak tahu tentang ViewModel.
 * Ini membuatnya mudah untuk di-preview.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddAddressContent(
    uiState: AddAddressUiState,
    onBackClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onProvinceSelected: (Int) -> Unit,
    onCitySelected: (Int) -> Unit,
    onDistrictSelected: (Int) -> Unit,
    onStreetAddressChange: (String) -> Unit,
    onSetMainAddress: (Boolean) -> Unit,
    onSaveAddress: () -> Unit

) {
    var provinceExpanded by remember { mutableStateOf(false) }
    var cityExpanded by remember { mutableStateOf(false) }
    var districtExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Address") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Contact", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = uiState.name,
                onValueChange = onNameChange,
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.phone,
                onValueChange = onPhoneChange,
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text("Address", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))

            AddressDropdown(
                label = "Province",
                expanded = provinceExpanded,
                onExpandedChange = { provinceExpanded = it },
                selectedValue = uiState.selectedProvince?.nama ?: "",
                options = uiState.provinceList.map { it.nama },
                onOptionSelected = onProvinceSelected,
            )
            Spacer(modifier = Modifier.height(16.dp))

            AddressDropdown(
                label = "City/District",
                expanded = cityExpanded,
                onExpandedChange = { cityExpanded = it },
                selectedValue = uiState.selectedCity?.nama ?: "",
                options = uiState.cityList.map { it.nama },
                onOptionSelected = onCitySelected,
                enabled = uiState.selectedProvince != null
            )
            Spacer(modifier = Modifier.height(16.dp))

            AddressDropdown(
                label = "Subdistrict",
                expanded = districtExpanded,
                onExpandedChange = { districtExpanded = it },
                selectedValue = uiState.selectedDistrict?.nama ?: "",
                options = uiState.districtList.map { it.nama },
                onOptionSelected = onDistrictSelected,
                enabled = uiState.selectedCity != null
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.postalCode,
                onValueChange = {},
                readOnly = true,
                label = { Text("Postal Code") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.streetAddress,
                onValueChange = onStreetAddressChange,
                label = { Text("Street Name, Building, No. House") },
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Set as Primary Address",color = Color.Black ,style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = uiState.isMainAddress,
                    onCheckedChange = onSetMainAddress
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onSaveAddress,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Save")
            }
        }
    }
}

/**
 * 3. REUSABLE COMPONENT: Komponen dropdown yang bisa dipakai ulang.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddressDropdown(
    label: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (Int) -> Unit,
    enabled: Boolean = true,


) {
    ExposedDropdownMenuBox(
        expanded = expanded && enabled,
        onExpandedChange = { if (enabled) onExpandedChange(!expanded) }
    ) {
        OutlinedTextField(
            value = if (selectedValue.isBlank() && enabled) "Choose $label" else selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            enabled = enabled
        )
        ExposedDropdownMenu(
            expanded = expanded && enabled,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(index)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

/**
 * 4. PREVIEW: Untuk menampilkan UI di Android Studio tanpa menjalankan aplikasi.
 */
@Preview(showBackground = true, name = "Add Address Screen")
@Composable
fun AddAddressScreenPreview() {
    val fakeProvince = LocationData.Provinsi(
        nama = "Nanggroe Aceh Darussalam",
        daftarkabupatenKota = listOf(
            LocationData.KabupatenKota("Kab. Aceh Barat", emptyList())
        )
    )
    val fakeUiState = AddAddressUiState(
        name = "Budi Santoso",
        phone = "081234567890",
        streetAddress = "Jl. Kemerdekaan No. 17",
        provinceList = listOf(fakeProvince),
        selectedProvince = fakeProvince,
        cityList = fakeProvince.daftarkabupatenKota,
        isMainAddress = true
    )

    UnicaseTheme {
        AddAddressContent(
            uiState = fakeUiState,
            onBackClick = {}, onNameChange = {}, onPhoneChange = {},
            onProvinceSelected = {}, onCitySelected = {}, onDistrictSelected = {},
            onStreetAddressChange = {}, onSetMainAddress = {}, onSaveAddress = {}
        )
    }
}