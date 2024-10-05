package com.example.adminkklunissula.data.repository

import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DaftarKKLRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) {
    fun getDaftarKKL(callback: (Resource<List<DaftarKKLLuarNegeri>>) -> Unit) {
        callback(Resource.Loading) // Set status loading

        firebaseFirestore.collection("daftar_KKLdalamnegeri")
            .get()
            .addOnSuccessListener { result ->
                val daftarKKLList = result.documents.mapNotNull { document ->
                    document.toObject(DaftarKKLLuarNegeri::class.java)
                }
                callback(Resource.Success(daftarKKLList)) // Berhasil, kirim data
            }
            .addOnFailureListener { exception ->
                callback(Resource.Error(exception)) // Jika gagal, kirim error
            }
    }


}