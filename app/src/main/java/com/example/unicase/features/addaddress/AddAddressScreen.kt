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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.features.customization.Address
import com.example.unicase.features.customization.CustomizationViewModel
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen(
    navController: NavController,
    customizationViewModel: CustomizationViewModel
) {
    // State untuk input form
    var recipientName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var streetAddress by remember { mutableStateOf("") }
    var addressLabel by remember { mutableStateOf("Rumah") }
    var isMainAddress by remember { mutableStateOf(false) }

    // State untuk lokasi terpilih (hanya untuk UI)
    var selectedProvince by remember { mutableStateOf<String?>(null) }
    var selectedCity by remember { mutableStateOf<String?>(null) }
    var selectedDistrict by remember { mutableStateOf<String?>(null) }
    var postalCode by remember { mutableStateOf("") }

    // Ambil daftar lokasi dari ViewModel
    val provinceList = customizationViewModel.provinceList
    val cityList by customizationViewModel.cityList
    val districtList by customizationViewModel.districtList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Address") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Contact", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            OutlinedTextField(value = recipientName, onValueChange = { recipientName = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier.fillMaxWidth(), singleLine = true)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Address", style = MaterialTheme.typography.titleMedium, color = Color.Black)

            // Dropdown Provinsi
            AddressDropdown(
                label = "Province",
                selectedValue = selectedProvince ?: "",
                options = provinceList.map { it.nama },
                onOptionSelected = { index ->
                    val province = provinceList[index]
                    selectedProvince = province.nama
                    customizationViewModel.onProvinceSelected(province)
                    // Reset pilihan di bawahnya
                    selectedCity = null
                    selectedDistrict = null
                    postalCode = ""
                }
            )

            // Dropdown Kota/Kabupaten
            AddressDropdown(
                label = "City/District",
                selectedValue = selectedCity ?: "",
                options = cityList.map { it.nama },
                onOptionSelected = { index ->
                    val city = cityList[index]
                    selectedCity = city.nama
                    customizationViewModel.onCitySelected(city)
                    // Reset pilihan di bawahnya
                    selectedDistrict = null
                    postalCode = ""
                },
                enabled = selectedProvince != null
            )

            // Dropdown Kecamatan
            AddressDropdown(
                label = "Subdistrict",
                selectedValue = selectedDistrict ?: "",
                options = districtList.map { it.nama },
                onOptionSelected = { index ->
                    val district = districtList[index]
                    selectedDistrict = district.nama
                    postalCode = district.daftarKodePos.firstOrNull() ?: ""
                },
                enabled = selectedCity != null
            )

            OutlinedTextField(value = postalCode, onValueChange = {}, readOnly = true, label = { Text("Postal Code") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = streetAddress, onValueChange = { streetAddress = it }, label = { Text("Street Name, Building, No. House") }, modifier = Modifier.fillMaxWidth().height(120.dp))
            OutlinedTextField(value = addressLabel, onValueChange = { addressLabel = it }, label = { Text("Address Label (e.g., Home, Office)") }, modifier = Modifier.fillMaxWidth())

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Set as Primary Address", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                Switch(checked = isMainAddress, onCheckedChange = { isMainAddress = it })
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val fullAddressString = "$streetAddress, $selectedDistrict, $selectedCity, $selectedProvince, $postalCode"
                    val newAddress = Address(
                        recipientName = recipientName,
                        phoneNumber = phoneNumber,
                        fullAddress = fullAddressString,
                        label = addressLabel,
                        isSelected = isMainAddress
                    )
                    customizationViewModel.addAddress(newAddress)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = recipientName.isNotBlank() && phoneNumber.isNotBlank() && streetAddress.isNotBlank() && selectedProvince != null && selectedCity != null && selectedDistrict != null
            ) {
                Text("Save Address")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddressDropdown(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (Int) -> Unit,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && enabled,
        onExpandedChange = { if (enabled) expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedValue.ifEmpty { "Choose $label" },
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            enabled = enabled
        )
        ExposedDropdownMenu(
            expanded = expanded && enabled,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(index)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddAddressScreenPreview() {
    UnicaseTheme {
        // Karena ViewModel diperlukan, kita tidak bisa memanggil AddAddressScreen secara langsung
        // di preview. Namun, kita bisa membuat NavController palsu dan ViewModel palsu
        // jika ingin melihat preview yang lebih akurat.
        val navController = rememberNavController()
        val fakeViewModel = CustomizationViewModel()
        AddAddressScreen(navController = navController, customizationViewModel = fakeViewModel)
    }
}