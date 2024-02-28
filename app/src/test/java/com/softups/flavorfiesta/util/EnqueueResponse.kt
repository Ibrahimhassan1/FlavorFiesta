package com.softups.flavorfiesta.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

// Attribute: https://proandroiddev.com/testing-retrofit-converter-with-mock-webserver-50f3e1f54013
fun MockWebServer.enqueueResponse(fileName: String, httpCode: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(httpCode)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}