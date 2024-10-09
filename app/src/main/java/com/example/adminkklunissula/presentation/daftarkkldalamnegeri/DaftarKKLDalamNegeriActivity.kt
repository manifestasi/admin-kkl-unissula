package com.example.adminkklunissula.presentation.daftarkkldalamnegeri

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminkklunissula.R
import com.example.adminkklunissula.data.model.DaftarKKLDalamNegeri
import com.example.adminkklunissula.databinding.ActivityDaftarKkldalamNegeriBinding
import com.example.adminkklunissula.presentation.daftarkklluarnegeri.DaftarKKLLuarNegeriViewModel
import com.example.adminkklunissula.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaftarKKLDalamNegeriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaftarKkldalamNegeriBinding
    private lateinit var daftarKKLAdapter: DaftarKKLDalamNegeriAdapter
    private lateinit var viewModel: DaftarKKLLuarNegeriViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDaftarKkldalamNegeriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbar.tvTitlePage.text = getString(R.string.data_kkl_dalam_negeri)
        binding.recyclerKklluar.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[DaftarKKLLuarNegeriViewModel::class.java]

        // Observe LiveData dari ViewModel
        viewModel.daftarKKLListDn.observe(this) { resource ->
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
                        daftarKKLAdapter = DaftarKKLDalamNegeriAdapter(daftarKKLList)
                        binding.recyclerKklluar.adapter = daftarKKLAdapter

                        daftarKKLAdapter.setOnClickCallback(object : DaftarKKLDalamNegeriAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: DaftarKKLDalamNegeri) {
                                val intent = Intent(this@DaftarKKLDalamNegeriActivity, DetailKKLDalamNegeriActivity::class.java)
                                intent.putExtra("data", data)
                                startActivity(intent)
                            }

                        })
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
        viewModel.fetchDaftarKKLDalamNegeri(COLLECTION)

        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }


    }

    companion object {
        const val COLLECTION = "daftar_KKLdalamnegeri"
    }


}