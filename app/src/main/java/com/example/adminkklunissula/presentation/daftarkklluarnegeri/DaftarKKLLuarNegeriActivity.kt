package com.example.adminkklunissula.presentation.daftarkklluarnegeri

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminkklunissula.R
import com.example.adminkklunissula.databinding.ActivityDaftarKklluarNegeriBinding
import com.example.adminkklunissula.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaftarKKLLuarNegeriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarKklluarNegeriBinding
    private lateinit var daftarKKLAdapter: DaftarKKLLuarNegeriAdapter
    private lateinit var viewModel: DaftarKKLLuarNegeriViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarKklluarNegeriBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerKklluar.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[DaftarKKLLuarNegeriViewModel::class.java]

        // Observe LiveData dari ViewModel
        viewModel.daftarKKLList.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.recyclerKklluar.visibility = View.GONE
                    //errorTextView.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.recyclerKklluar.visibility = View.VISIBLE
                    //errorTextView.visibility = View.GONE

                    resource.data?.let { daftarKKLList ->
                        daftarKKLAdapter = DaftarKKLLuarNegeriAdapter(daftarKKLList)
                        binding.recyclerKklluar.adapter = daftarKKLAdapter
                    }
                }
                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.recyclerKklluar.visibility = View.GONE
                    //errorTextView.visibility = View.VISIBLE
                    //errorTextView.text = "Error: ${resource.exception.message}"
                }

                else -> {}
            }
        }

        // Fetch data dari Firestore
        viewModel.fetchDaftarKKL()

    }


}