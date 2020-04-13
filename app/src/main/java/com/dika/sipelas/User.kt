package com.dika.sipelas

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var nama: String? = "",
    var nim: String? = "",
    var telp: String? = "",
    var status: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nama" to nama,
            "nim" to nim,
            "telp" to telp,
            "status" to status
        )
    }
}