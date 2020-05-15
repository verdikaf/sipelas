package com.dika.sipelas

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Jadwal(
    var keterangan: String? = "",
    var hari: String? = "",
    var ruang: String? = "",
    var jam: String? = "",
    var hari_ruang: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "hari" to hari,
            "jam" to jam,
            "ruang" to ruang,
            "keterangan" to keterangan,
            "hari_ruang" to hari_ruang
        )
    }

}