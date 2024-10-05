package com.example.adminkklunissula.presentation.daftarkklluarnegeri

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminkklunissula.data.model.DaftarKKLDalamNegeri
import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.data.repository.DaftarKKLRepository
import com.example.adminkklunissula.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaftarKKLLuarNegeriViewModel @Inject constructor(
    private val daftarKKLRepository: DaftarKKLRepository
) : ViewModel() {
//    private val _daftarKKLList = MutableLiveData<Resource<List<DaftarKKLLuarNegeri>>>()
//    val daftarKKLList: LiveData<Resource<List<DaftarKKLLuarNegeri>>> get() = _daftarKKLList

    private val _daftarKKLList = MutableLiveData<Resource<List<DaftarKKLLuarNegeri>>>()
    val daftarKKLList: LiveData<Resource<List<DaftarKKLLuarNegeri>>> get() = _daftarKKLList

    private val _daftarKKLListDn = MutableLiveData<Resource<List<DaftarKKLDalamNegeri>>>()
    val daftarKKLListDn: LiveData<Resource<List<DaftarKKLDalamNegeri>>> get() = _daftarKKLListDn

    private val _saveResult = MediatorLiveData<Resource<Void>>()
    val saveResult: LiveData<Resource<Void>> = _saveResult

    fun fetchDaftarKKL(collection: String) {
        viewModelScope.launch {
            _daftarKKLList.value = Resource.Loading
            val result = daftarKKLRepository.getDaftarKKL(collection)
            _daftarKKLList.value = result
        }
    }

    fun fetchDaftarKKLDalamNegeri(collection: String) {
        viewModelScope.launch {
            _daftarKKLListDn.value = Resource.Loading
            val result = daftarKKLRepository.getDaftarKKLDalamNegeri(collection)
            _daftarKKLListDn.value = result
        }
    }

    fun updateDataInFirestore(collection: String,document:String, data: MutableMap<String, Any>) {
        viewModelScope.launch {
            _saveResult.value = Resource.Loading
            try {
                daftarKKLRepository.updateDataInFirestore(collection, document, data)
                _saveResult.value = Resource.Success(null)
            } catch (e: Exception) {
                _saveResult.value = Resource.Error(e)
            }
        }
    }

}