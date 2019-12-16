package com.magic.Owners.data.json_parser

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
class JsonParser(private val fileName: String, val context: Context): Readable {

    override fun text(): String {
        val inputStream = context.assets.open(fileName)
        val br = BufferedReader(InputStreamReader(inputStream))
        br.use { reader ->
            val sb = StringBuilder()
            var line = reader.readLine()

            while (line != null) {
                sb.append(line)
                sb.append(System.lineSeparator())
                line = reader.readLine()
            }
            return sb.toString()
        }
    }
}