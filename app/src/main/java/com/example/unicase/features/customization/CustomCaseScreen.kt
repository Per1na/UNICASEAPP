package com.example.unicase.features.customization

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme
import java.util.UUID

sealed class DesignLayer(
    val id: UUID = UUID.randomUUID(),
    var scale: MutableState<Float> = mutableStateOf(1f),
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var rotation: MutableState<Float> = mutableStateOf(0f)
)

data class ImageLayer(val uri: Uri) : DesignLayer()
data class TextLayer(var text: String, var color: MutableState<Color>) : DesignLayer()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCaseScreen(navController: NavController) {
    var layers by remember { mutableStateOf<List<DesignLayer>>(emptyList()) }
    var selectedLayerId by remember { mutableStateOf<UUID?>(null) }
    var showAddTextDialog by remember { mutableStateOf(false) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                val newImageLayer = ImageLayer(uri)
                val existingImageIndex = layers.indexOfFirst { it is ImageLayer }

                val newLayers = layers.toMutableList()
                if (existingImageIndex != -1) {
                    newLayers[existingImageIndex] = newImageLayer
                } else {
                    newLayers.add(newImageLayer)
                }
                layers = newLayers
                selectedLayerId = newImageLayer.id
            }
        }
    )

    if (showAddTextDialog) {
        AddTextDialog(
            onDismiss = { showAddTextDialog = false },
            onConfirm = { text, color ->
                if (text.isNotEmpty()) {
                    val newLayer = TextLayer(text, mutableStateOf(color))
                    layers = layers + newLayer
                    selectedLayerId = newLayer.id
                }
                showAddTextDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Custom Case", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
                    Text("Add to Cart")
                }
                Button(onClick = { navController.navigate("checkout") }, modifier = Modifier.weight(1f)) {
                    Text("Checkout")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 3f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .aspectRatio(10f / 19.5f)
                        .clipToBounds()
                        .clip(RoundedCornerShape(24.dp))
                        .pointerInput(selectedLayerId) {
                            detectTransformGestures { _, pan, zoom, rotationChange ->
                                val selectedLayer = layers.find { it.id == selectedLayerId }
                                selectedLayer?.let {
                                    it.scale.value *= zoom
                                    it.offsetX.value += pan.x
                                    it.offsetY.value += pan.y
                                    it.rotation.value += rotationChange
                                }
                            }
                        }
                ) {
                    layers.forEach { layer ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { selectedLayerId = layer.id }
                                .graphicsLayer(
                                    scaleX = layer.scale.value,
                                    scaleY = layer.scale.value,
                                    translationX = layer.offsetX.value,
                                    translationY = layer.offsetY.value,
                                    rotationZ = layer.rotation.value
                                )
                                .border(
                                    width = if (layer.id == selectedLayerId) 2.dp else 0.dp,
                                    color = if (layer.id == selectedLayerId) PrimaryBlue else Color.Transparent
                                )
                        ) {
                            when (layer) {
                                is ImageLayer -> {
                                    AsyncImage(
                                        model = layer.uri,
                                        contentDescription = "Image Layer",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                is TextLayer -> {
                                    Text(
                                        text = layer.text,
                                        color = layer.color.value,
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.case_template),
                    contentDescription = "Case Template",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(150.dp)
                        .aspectRatio(10f / 19.5f)
                        .clip(RoundedCornerShape(24.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val imageLayerExists = layers.any { it is ImageLayer }
                Button(onClick = { galleryLauncher.launch("image/*") }) {
                    Text(if (imageLayerExists) "Change Image" else "Add Image")
                }
                OutlinedButton(onClick = { showAddTextDialog = true }) {
                    Text("Add Text")
                }
            }

            if (layers.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Layers", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(layers) { index, layer ->
                        val isSelected = layer.id == selectedLayerId
                        val buttonText = when (layer) {
                            is ImageLayer -> "Image"
                            is TextLayer -> layer.text
                        }
                        OutlinedButton(
                            onClick = { selectedLayerId = layer.id },
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (isSelected) PrimaryBlue else Color.Transparent,
                            )
                        ) {
                            Text(text = buttonText, color = if (isSelected) Color.White else PrimaryBlue)
                        }
                    }
                }
                Button(
                    onClick = {
                        if(selectedLayerId != null) {
                            layers = layers.filterNot { it.id == selectedLayerId }
                            selectedLayerId = layers.lastOrNull()?.id
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    enabled = selectedLayerId != null,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Layer")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Selected Layer")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            CustomizationOptionSection()
        }
    }
}

@Composable
fun AddTextDialog(onDismiss: () -> Unit, onConfirm: (String, Color) -> Unit) {
    var text by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.White) }
    val colors = listOf(Color.White, Color.Black, Color.Red, Color.Blue, Color.Yellow)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add/Edit Custom Text") },
        text = {
            Column {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Your Text") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Color:")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
                    items(colors) { color ->
                        val isSelected = selectedColor == color
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) PrimaryBlue else Color.Gray,
                                    shape = CircleShape
                                )
                                .clickable { selectedColor = color }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(text, selectedColor) },
                enabled = text.isNotEmpty()
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationOptionSection() {

    val brands = listOf("Samsung", "Apple", "Xiaomi", "Oppo", "Vivo")

    val phoneTypes = mapOf(
        "Samsung" to listOf("Samsung Z Flip5", "Galaxy S24 Ultra", "Galaxy A55"),
        "Apple" to listOf("iPhone 15 Pro Max", "iPhone 15", "iPhone 14"),
        "Xiaomi" to listOf("Xiaomi 14", "Redmi Note 13 Pro", "Poco F6")
    )

    var selectedBrand by remember { mutableStateOf(brands.first()) }
    var selectedType by remember { mutableStateOf(phoneTypes[selectedBrand]?.first() ?: "") }
    var isBrandDropdownExpanded by remember { mutableStateOf(false) }
    var isTypeDropdownExpanded by remember { mutableStateOf(false) }
    var additionalDescription by remember { mutableStateOf("") }

    LaunchedEffect(selectedBrand) {
        selectedType = phoneTypes[selectedBrand]?.first() ?: ""
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column {
            Text("Case Type", style = MaterialTheme.typography.titleMedium, fontFamily = Poppins, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("Hardcase", "Softcase", "Premium anti crack")) { text -> OutlinedButton(onClick = {}) { Text(text) } }
            }
        }
        Column {
            Text("Print Effect", style = MaterialTheme.typography.titleMedium, fontFamily = Poppins, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("Glossy", "Doff", "Glow Effect")) { text -> OutlinedButton(onClick = {}) { Text(text) } }
            }
        }

        Column {
            Text("Select brand",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = isBrandDropdownExpanded,
                onExpandedChange = { isBrandDropdownExpanded = !isBrandDropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedBrand,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isBrandDropdownExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isBrandDropdownExpanded,
                    onDismissRequest = { isBrandDropdownExpanded = false }
                ) {
                    brands.forEach { brand ->
                        DropdownMenuItem(
                            text = { Text(brand) },
                            onClick = {
                                selectedBrand = brand
                                isBrandDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Column {
            Text("Select Type",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = isTypeDropdownExpanded,
                onExpandedChange = { isTypeDropdownExpanded = !isTypeDropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedType,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isTypeDropdownExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isTypeDropdownExpanded,
                    onDismissRequest = { isTypeDropdownExpanded = false }
                ) {
                    phoneTypes[selectedBrand]?.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                selectedType = type
                                isTypeDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        OutlinedTextField(
            value = additionalDescription,
            onValueChange = { additionalDescription = it },
            label = { Text("Deskripsi tambahan") },
            modifier = Modifier.fillMaxWidth().height(120.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Info, contentDescription = "Info", modifier = Modifier.size(16.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Casing Galaxy Z Flip dikenakan biaya tambahan Rp 10.000 karena bahan khusus.", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            PriceRow(label = "Case Type",price = "Rp35.000")
            Spacer(modifier = Modifier.height(8.dp))
            PriceRow(label = "Print Effect", price = "Rp8.000")
        }
    }
}


@Composable
fun PriceRow(label: String, price: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.LightGray)
        Text(price, fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCustomCaseScreen() {
    UnicaseTheme {
        CustomCaseScreen(rememberNavController())
    }
}