package co.edu.udistrital.esping.pgs.api.visits.repository

import co.edu.udistrital.esping.pgs.api.visits.dto.PushyPushRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object NotificationRepository {

    private const val PARAM_API_KEY = "api_key"
    private const val HEADER_CONTENT = "Content-Type"
    private const val MEDIA_TYPE = "application/json"

    fun dispatchNotification(baseURL: String, secretApiKey: String, pushRequestDto: PushyPushRequestDto): Int {
        val urlBuilder = baseURL.toHttpUrlOrNull()?.newBuilder()
        urlBuilder?.addQueryParameter(PARAM_API_KEY, secretApiKey)

        val url = urlBuilder?.build()

        val objectMapper = ObjectMapper()
        val jsonRequest = objectMapper.writeValueAsString(pushRequestDto)
        val okHttpRequest = jsonRequest.toRequestBody(MEDIA_TYPE.toMediaType())

        val request = url?.let {
            Request.Builder().url(it).addHeader(HEADER_CONTENT, MEDIA_TYPE).post(okHttpRequest).build()
        }

        val client = OkHttpClient()
        val call = request?.let { client.newCall(it) }
        val response = call?.execute()

        if (response != null) {
            if(response.isSuccessful){
                response.body?.close()
            }
        }

        return response?.code ?: 0
    }
}