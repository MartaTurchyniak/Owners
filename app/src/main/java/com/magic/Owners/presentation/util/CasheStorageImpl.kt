package com.magic.Owners.presentation.util

import java.io.File
import android.content.Context

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */

class CacheStorageImpl(private val context: Context) : CasheStorage {

    override fun newFile(name: String): File {
        val storageDir = context.cacheDir.absolutePath + "/owners"

        val file = File(storageDir, name).also { it.mkdirs() }
        if (file.exists()) {
            file.delete()
        }
        return file
    }
}