package com.example.adminkklunissula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarKKLDalamNegeri(
    val documentId: String = "",
    val buktiUrl: String = "",
    val email: String = "",
    val fotoUrl: String = "",
    val jenisKelamin: String = "",
    val ktpUrl: String = "",
    val nama: String = "",
    val nim: String = "",
    val noHp: String = "",
    val smtKelas: String = "",
    val status: String = ""
) : Parcelable

