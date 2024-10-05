package com.example.adminkklunissula.presentation.daftarkklluarnegeri

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.data.repository.DaftarKKLRepository
import com.example.adminkklunissula.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaftarKKLLuarNegeriViewModel @Inject constructor(
    private val daftarKKLRepository: DaftarKKLRepository
):ViewModel(){
    private val _daftarKKLList = MutableLiveData<Resource<List<DaftarKKLLuarNegeri>>>()
    val daftarKKLList: LiveData<Resource<List<DaftarKKLLuarNegeri>>> get() = _daftarKKLList

    fun fetchDaftarKKL() {
        viewModelScope.launch {
            daftarKKLRepository.getDaftarKKL { resource ->
                _daftarKKLList.value = resource
            }
        }
    }

}