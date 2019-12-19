package com.magic.Owners.presentation.util



import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
object Permissions {

    const val REQUEST_CODE_STORAGE = 111
    const val REQUEST_CODE_CAMERA = 112

    val PERMISSIONS_STORAGE = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val PERMISSIONS_CAMERA = PERMISSIONS_STORAGE + listOf(Manifest.permission.CAMERA)
}

fun Context.permissionsGranted(permissions: List<String>): Boolean {
    return EasyPermissions.hasPermissions(this, *permissions.toTypedArray())
}

fun Fragment.requestPermissions(
    permissions: List<String>,
    requestCode: Int,
    rationale: String
) {
    EasyPermissions.requestPermissions(
        this,
        rationale,
        requestCode,
        *permissions.toTypedArray()
    )
}

fun Activity.requestPermissions(
    permissions: List<String>,
    requestCode: Int,
    rationale: String
) {
    EasyPermissions.requestPermissions(
        this,
        rationale,
        requestCode,
        *permissions.toTypedArray()
    )
}