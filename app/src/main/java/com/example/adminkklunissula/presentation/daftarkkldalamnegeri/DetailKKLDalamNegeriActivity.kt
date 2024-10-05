package com.example.adminkklunissula.presentation.daftarkkldalamnegeri

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.adminkklunissula.R
import com.example.adminkklunissula.data.model.DaftarKKLDalamNegeri
import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.databinding.ActivityDetailKkldalamNegeriBinding
import com.example.adminkklunissula.presentation.daftarkklluarnegeri.DaftarKKLLuarNegeriViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailKKLDalamNegeriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKkldalamNegeriBinding
    private lateinit var viewModel: DaftarKKLLuarNegeriViewModel
    private var documentId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailKkldalamNegeriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[DaftarKKLLuarNegeriViewModel::class.java]

        binding.toolbar.tvTitlePage.text = getString(R.string.data_kkl_dalam_negeri)
        val data : DaftarKKLDalamNegeri? = intent.getParcelableExtra("data")
        data?.let {
            documentId = it.documentId
            binding.etNama.setText(it.nama)
            binding.etNim.setText(it.nim)
            binding.etNohp.setText(it.noHp)
            binding.etSmtkelas.setText(it.smtKelas)
            binding.etEmail.setText(it.email)
            val jenisKelamin = it.jenisKelamin
            when (jenisKelamin) {
                "Laki-Laki" -> binding.radioGroup.check(R.id.radio_button_1)
                "Perempuan" -> binding.radioGroup.check(R.id.radio_button_2)
            }
            Glide.with(this)
                .load(it.ktpUrl)
                .into(binding.ivPlaceholder)

            Glide.with(this)
                .load(it.fotoUrl)
                .into(binding.ivPlaceholderFoto)

            Glide.with(this)
                .load(it.buktiUrl)
                .into(binding.ivPlaceholderBukti)
        }

        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnTerima.setOnClickListener {
            val data = mutableMapOf<String, Any>(
                "status" to "2"
            )
            documentId?.let { it1 -> viewModel.updateDataInFirestore(COLLECTION, it1, data) }
        }
        binding.btnTolak.setOnClickListener {
            val data = mutableMapOf<String, Any>(
                "status" to "3"
            )
            documentId?.let { it1 -> viewModel.updateDataInFirestore(COLLECTION, it1, data) }
        }

    }
    companion object {
        const val COLLECTION = "daftar_KKLdalamnegeri"
    }
}