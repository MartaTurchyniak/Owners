package com.magic.Owners.data.interceptors

import com.magic.Owners.data.interceptors.headers.HeaderStorage
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.LinkedHashMap

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
class HeadersInterceptor(private val headersStorage: HeaderStorage) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val headers = getHeadersForRequest(chain.request())
        val request = chain.request().newBuilder().headers(headers).build()

        return chain.proceed(request)
    }

    private fun getHeadersForRequest(request: Request): Headers {
        return headersStorage.apiHeaders(headersToMap(request.headers)).toHeaders()
    }

    private fun headersToMap(headers: Headers): Map<String, String> {
        val headersMap = LinkedHashMap<String, String>()

        for (i in 0 until headers.size) {
            headersMap[headers.name(i)] = headers.value(i)
        }

        return headersMap
    }
}