package com.example.adminkklunissula.data.repository

import com.example.adminkklunissula.data.model.DaftarKKLDalamNegeri
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
    suspend fun getDaftarKKL(collection: String): Resource<List<DaftarKKLLuarNegeri>> {
        return try {
            val snapshot = firebaseFirestore.collection(collection).get().await()
            val daftarKKLList = snapshot.documents.map { document ->
                val data = document.toObject(DaftarKKLLuarNegeri::class.java)
                data?.copy(documentId = document.id) // Ambil nama dokumen dan simpan di field documentId
            }.filterNotNull() // Filter data yang null jika ada
            Resource.Success(daftarKKLList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
    suspend fun getDaftarKKLDalamNegeri(collection: String): Resource<List<DaftarKKLDalamNegeri>> {
        return try {
            val snapshot = firebaseFirestore.collection(collection).get().await()
            val daftarKKLList = snapshot.documents.map { document ->
                val data = document.toObject(DaftarKKLDalamNegeri::class.java)
                data?.copy(documentId = document.id) // Ambil nama dokumen dan simpan di field documentId
            }.filterNotNull() // Filter data yang null jika ada
            Resource.Success(daftarKKLList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    suspend fun updateDataInFirestore(collection: String,document:String, data: MutableMap<String, Any>) {
        withContext(Dispatchers.IO) {
            firebaseFirestore.collection(collection).document(document).update(data).await()
        }
    }




}