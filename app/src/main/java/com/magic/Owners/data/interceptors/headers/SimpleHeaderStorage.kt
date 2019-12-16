package com.magic.Owners.data.interceptors.headers

import java.util.HashMap

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
private const val HEADER_NAME_CONTENT_TYPE = "Content-Type"
private const val CONTENT_TYPE_JSON = "application/json"
private const val HEADER_NAME_ACCESS_TOKEN = "Authorization"

class SimpleHeaderStorage() : HeaderStorage {

    override fun apiHeaders(existingHeaders: Map<String, String>): Map<String, String> {
        val headers = HashMap(existingHeaders)
        headers[HEADER_NAME_CONTENT_TYPE] = CONTENT_TYPE_JSON
        return headers
    }
}