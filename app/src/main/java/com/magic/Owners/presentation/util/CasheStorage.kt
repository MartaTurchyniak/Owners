package com.magic.Owners.presentation.util

import java.io.File

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
interface CasheStorage {
    fun newFile(name: String): File
}