package com.example.adminkklunissula.presentation.daftarkklluarnegeri

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.adminkklunissula.R
import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.databinding.ActivityDetailKklluarNegeriBinding

class DetailKKLLuarNegeriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKklluarNegeriBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailKklluarNegeriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.tvTitlePage.text = getString(R.string.data_kkl_luar_negeri)
        val data :DaftarKKLLuarNegeri? = intent.getParcelableExtra("data")
        data?.let {
          binding.etNama.setText(it.nama)
            binding.etNim.setText(it.nim)
            binding.etNohp.setText(it.noHp)
            binding.etSmtkelas.setText(it.smtKelas)
            val jenisKelamin = it.jenisKelamin
            when (jenisKelamin) {
                "Laki-Laki" -> binding.radioGroup.check(R.id.radio_button_1)
                "Perempuan" -> binding.radioGroup.check(R.id.radio_button_2)
            }
            Glide.with(this)
                .load(it.ktpUrl)
                .into(binding.ivPlaceholder)

            Glide.with(this)
                .load(it.ktpUrl)
                .into(binding.ivPlaceholderPaspor)

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
    }


}