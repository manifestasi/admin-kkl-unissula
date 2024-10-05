package com.example.adminkklunissula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarKKLLuarNegeri(
    val buktiUrl: String = "",
    val email: String = "",
    val fotoUrl: String = "",
    val jenisKelamin: String = "",
    val ktpUrl: String = "",
    val nama: String = "",
    val nim: String = "",
    val noHp: String = "",
    val smtKelas: String = ""
) : Parcelable

