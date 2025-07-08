package com.example.unicase.features.customization

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import com.example.unicase.R
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.UnicaseTheme
import com.example.unicase.util.GlobalKey
import com.example.unicase.util.captureAndSaveDesign
import kotlinx.coroutines.launch

data class ImageData(
    val uri: Uri,
    var scale: MutableState<Float> = mutableStateOf(1f),
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var rotation: MutableState<Float> = mutableStateOf(0f)
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCaseScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val captureKey = remember { GlobalKey() }

    var images by remember { mutableStateOf<List<ImageData>>(emptyList()) }
    var selectedImageIndex by remember { mutableStateOf(0) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null && images.size < 4) {
                images = images + ImageData(uri)
                selectedImageIndex = images.lastIndex
            }
        }
    )

    val selectedImage = images.getOrNull(selectedImageIndex)

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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    scope.launch {
                        val savedFile = captureAndSaveDesign(context, captureKey) {
                            Box(
                                modifier = Modifier
                                    .width(240.dp)
                                    .height(480.dp)
                                    .background(Color(0xFFF0F0F0)),
                                contentAlignment = Alignment.Center
                            ) {
                                images.forEach {
                                    AsyncImage(
                                        model = it.uri,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .graphicsLayer(
                                                scaleX = it.scale.value,
                                                scaleY = it.scale.value,
                                                translationX = it.offsetX.value,
                                                translationY = it.offsetY.value,
                                                rotationZ = it.rotation.value
                                            )
                                    )
                                }

                                Image(
                                    painter = painterResource(id = R.drawable.case_template),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                        Toast.makeText(context, "Saved to: ${savedFile.absolutePath}", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.weight(1f)) {
                    Text("Save Design")
                }

                Button(onClick = { /* TODO: Checkout logic */ }, modifier = Modifier.weight(1f)) {
                    Text("Checkout")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 3f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
                    .then(captureKey.modifier()),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .aspectRatio(10f / 19.5f)
                        .clipToBounds()
                        .clip(RoundedCornerShape(24.dp))
                        .pointerInput(selectedImage?.uri) {
                            detectTransformGestures { _, pan, zoom, rotationChange ->
                                selectedImage?.let {
                                    it.scale.value *= zoom
                                    it.offsetX.value += pan.x
                                    it.offsetY.value += pan.y
                                    it.rotation.value += rotationChange
                                }
                            }
                        }
                ) {
                    images.forEachIndexed { index, image ->
                        AsyncImage(
                            model = image.uri,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(
                                    scaleX = image.scale.value,
                                    scaleY = image.scale.value,
                                    translationX = image.offsetX.value,
                                    translationY = image.offsetY.value,
                                    rotationZ = image.rotation.value
                                )
                        )
                    }
                }

                // Case template overlay
                Image(
                    painter = painterResource(id = R.drawable.case_template),
                    contentDescription = "Case Template",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(150.dp)
                        .aspectRatio(10f / 19.5f)
                        .clip(RoundedCornerShape(24.dp))
                )

                // Trash icon for delete selected image
                if (selectedImage != null) {
                    IconButton(
                        onClick = {
                            if (selectedImageIndex in images.indices) {
                                images = images.toMutableList().also { it.removeAt(selectedImageIndex) }
                                selectedImageIndex = images.lastIndex.coerceAtLeast(0)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Selected", tint = Color.Red)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { galleryLauncher.launch("image/*") },
                enabled = images.size < 4
            ) {
                Text("Upload Your Image (${images.size}/4)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (images.isNotEmpty()) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    itemsIndexed(images) { index, _ ->
                        OutlinedButton(
                            onClick = { selectedImageIndex = index },
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (index == selectedImageIndex) Color(0xFF2885F0) else Color.Transparent,
                                contentColor = if (index == selectedImageIndex) Color.White else Color(0xFF2885F0)
                            )
                        ) {
                            Text("Image ${index + 1}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            CustomizationOptionSection()
        }
    }
}

@Composable
fun CustomizationOptionSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column {
            Text(
                "Case Type",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("Hardcase", "Softcase", "Premium anti crack")) { text ->
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text)
                    }
                }
            }
        }

        Column {
            Text(
                "Print Effect",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("Glossy", "Doff", "Glow Effect")) { text ->
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text)
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewCustomCaseScreen() {
    UnicaseTheme {
        CustomCaseScreen(rememberNavController())
    }
}
