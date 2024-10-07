package com.example.adminkklunissula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarKKLLuarNegeri(
    val documentId: String = "",
    val buktiUrl: String = "",
    val email: String = "",
    val fotoUrl: String = "",
    val pasporUrl: String = "",
    val jenisKelamin: String = "",
    val ktpUrl: String = "",
    val nama: String = "",
    val nim: String = "",
    val noHp: String = "",
    val smtKelas: String = "",
    val kotaBerangkat: String = "",
    val kotaPulang: String = "",
) : Parcelable

