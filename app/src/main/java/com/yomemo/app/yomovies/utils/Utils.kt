package com.yomemo.yomovies.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.maps.model.LatLng
import com.yomemo.yomovies.ui.LoginActivity

class Utils {
    companion object {
        fun getLocation(locationString: String): LatLng {
            val value = locationString.split(",")
            val lat = value[0].toDoubleOrNull()
            val long = value[1].toDoubleOrNull()
            if (lat != null && long != null)
                return LatLng(lat, long)
            return LatLng(0.0, 0.0)
        }

        fun signingOut(context: Context) {
            AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }
                }
        }

        val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
        val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    }
}