package com.example.alexey.maxi.util

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.RequiresPermission
import android.widget.Toast

/**
 * Метод осуществляет проверку наличия сети
 * @return true, если сеть на устройстве доступна
 **/
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnected(): Boolean {
    val connectivityManager = this
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}

/**
 * Метод сравнивает элементы в списках
 * @return true, если в списках имеется хотя бы один совпадающий элемент
 **/
fun <T> List<T>.findCongruentElement(list: List<T>) = this.intersect(list).isNotEmpty()


fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()