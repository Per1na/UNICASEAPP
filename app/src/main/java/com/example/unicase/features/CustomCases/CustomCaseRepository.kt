package com.example.unicase.CustomCases

import android.content.Context
import android.net.Uri
import com.example.unicase.features.CustomCases.CustomCaseRequest
import com.example.unicase.network.ApiService
import com.example.unicase.network.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class CustomCaseRepository @Inject constructor(
    private val api: ApiService
) {
    val repository = CustomCaseRepository(RetrofitInstance.apiService)

    suspend fun submitCustomCase(
        request: CustomCaseRequest,
        imageUri: Uri,
        context: Context
    ): Response<Any> {
        val contentResolver = context.contentResolver

        // Ambil file asli dari Uri
        val inputStream = contentResolver.openInputStream(imageUri)
        val tempFile = File.createTempFile("custom_case", ".jpg", context.cacheDir)
        inputStream?.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        val imagePart = MultipartBody.Part.createFormData(
            "image_file",
            tempFile.name,
            tempFile.asRequestBody("image/*".toMediaTypeOrNull())
        )

        val userIdPart = request.user_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val caseTypePart = request.case_type.toRequestBody("text/plain".toMediaTypeOrNull())
        val printEffectPart = request.print_effect.toRequestBody("text/plain".toMediaTypeOrNull())
        val brandIdPart = request.brand_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val brandTypeIdPart = request.brand_type_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionPart = request.description.toRequestBody("text/plain".toMediaTypeOrNull())

        return api.submitCustomCase(
            userId = userIdPart,
            caseType = caseTypePart,
            printEffect = printEffectPart,
            brandId = brandIdPart,
            brandTypeId = brandTypeIdPart,
            description = descriptionPart,
            imageFile = imagePart
        )
    }
}
