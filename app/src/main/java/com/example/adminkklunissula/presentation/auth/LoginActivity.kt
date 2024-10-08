package com.example.adminkklunissula.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.adminkklunissula.MainActivity
import com.example.adminkklunissula.R
import com.example.adminkklunissula.databinding.ActivityLoginBinding
import com.example.adminkklunissula.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //inisialisasi authviewmodel untuk memanggil fungsi login
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.btnMasuk.setOnClickListener {
            val nim = binding.etNim.text.toString().trim()
            val pass = binding.etPass.text.toString().trim()

            //cek isi nim dan password
            if (nim.isNotEmpty() && pass.isNotEmpty()) {
                authViewModel.loginUser(nim, pass)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        observerLogin()



    }
    private fun observerLogin(){
        authViewModel.loginResult.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Login failed: ${resource.exception.message}", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}