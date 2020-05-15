package com.dika.sipelas

class Peminjaman(
    var id : String,
    var nama : String,
    var kelas : String,
    var tanggal: String,
    var ruang: String,
    var jAwal: String,
    var jAkhir: String,
    var matkul: String,
    var alasan: String,
    var keterangan: String,
    var status: String,
    var hari: String) {
    constructor() : this("","", "", "", "", "","","", "", "", "", "")
}