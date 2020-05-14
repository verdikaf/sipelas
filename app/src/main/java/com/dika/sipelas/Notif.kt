package com.dika.sipelas

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Notif(
    var id: String? = "",
    var alasan: String? = "",
    var jAwal: String? = "",
    var jAkhir: String? = "",
    var kelas: String? = "",
    var keterangan: String? = "",
    var matkul: String? = "",
    var nama: String? = "",
    var ruang: String? = "",
    var tanggal: String? = "",
    var hari: String? = "",
    var status: String? =""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nama" to nama,
            "kelas" to kelas,
            "matkul" to matkul,
            "tanggal" to tanggal,
            "jAwal" to jAwal,
            "jAkhir" to jAkhir,
            "ruang" to ruang,
            "alasan" to alasan,
            "keterangan" to keterangan,
            "hari" to hari,
            "id" to id,
            "status" to status
        )
    }

}