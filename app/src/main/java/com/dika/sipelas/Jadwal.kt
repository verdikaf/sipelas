package com.dika.sipelas

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Jadwal(
    var alasan: String? = "",
    var jamAwal: String? = "",
    var jamAkhir: String? = "",
    var kelas: String? = "",
    var keterangan: String? = "",
    var matkul: String? = "",
    var nama: String? = "",
    var ruang: String? = "",
    var tgl_ruang: String? = "",
    var tgl: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nama" to nama,
            "kelas" to kelas,
            "matkul" to matkul,
            "tgl" to tgl,
            "jamAwal" to jamAwal,
            "jamAkhir" to jamAkhir,
            "ruang" to ruang,
            "alasan" to alasan,
            "keterangan" to keterangan,
            "tgl_ruang" to tgl_ruang
        )
    }

}