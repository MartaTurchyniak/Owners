package com.magic.Owners.data.interceptors.headers

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
interface HeaderStorage {
    fun apiHeaders(existingHeaders: Map<String, String>): Map<String, String>
}