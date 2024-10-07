package com.example.adminkklunissula.presentation.daftarkkldalamnegeri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
import com.example.adminkklunissula.util.Resource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        val data: DaftarKKLDalamNegeri? = intent.getParcelableExtra("data")
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
            showConfirmDialog1(documentId)
        }

        binding.btnTolak.setOnClickListener {
            showConfirmDialog2(documentId)
        }

        observeUpdate()

    }

    //fungsi untuk menampilkan loading data di awal
    private fun showLoading(isLoading: Boolean) {
        binding.progressindicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    //fungsi untuk menampilkan konfirmasi dialog update
    private fun showConfirmDialog1(documentId: String?) {
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.valid_dialog, null)
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        val saveButton: Button = dialogView.findViewById(R.id.btn_yakin)
        val batalButton: Button = dialogView.findViewById(R.id.btn_batal)
        val text: TextView = dialogView.findViewById(R.id.tv_title)

        text.text = getString(R.string.accept)
        saveButton.text = getString(R.string.update)
        saveButton.setOnClickListener {
            // Update data ke Firestore
            val data = mutableMapOf<String, Any>(
                "status" to "2" // Update status menjadi 2
            )

            documentId?.let { id ->
                viewModel.updateDataInFirestore(COLLECTION, id, data) // Update data di Firestore
            }
            dialog.dismiss()
        }
        batalButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    //fungsi untuk menampilkan konfirmasi dialog update
    private fun showConfirmDialog2(documentId: String?) {
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.valid_dialog, null)
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        val saveButton: Button = dialogView.findViewById(R.id.btn_yakin)
        val batalButton: Button = dialogView.findViewById(R.id.btn_batal)
        val text: TextView = dialogView.findViewById(R.id.tv_title)

        text.text = getString(R.string.reject)
        saveButton.text = getString(R.string.update)
        saveButton.setOnClickListener {
            // Update data ke Firestore
            val data = mutableMapOf<String, Any>(
                "status" to "3"
            )

            documentId?.let { id ->
                viewModel.updateDataInFirestore(COLLECTION, id, data) // Update data di Firestore
            }
            dialog.dismiss()
        }
        batalButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun observeUpdate() {
        viewModel.saveResult.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                }

                is Resource.Success -> {
                    showLoading(false)
                }

                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        "Failed to update data: ${resource.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }
    }

    companion object {
        const val COLLECTION = "daftar_KKLdalamnegeri"
    }
}