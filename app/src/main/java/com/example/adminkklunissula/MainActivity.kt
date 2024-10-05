package com.example.adminkklunissula

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminkklunissula.databinding.ActivityMainBinding
import com.example.adminkklunissula.presentation.daftarkkldalamnegeri.DaftarKKLDalamNegeriActivity
import com.example.adminkklunissula.presentation.daftarkklluarnegeri.DaftarKKLLuarNegeriActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLn.setOnClickListener {
            startActivity(Intent(this, DaftarKKLLuarNegeriActivity::class.java))
        }
        binding.btnDn.setOnClickListener {
            startActivity(Intent(this, DaftarKKLDalamNegeriActivity::class.java))
        }
    }

}