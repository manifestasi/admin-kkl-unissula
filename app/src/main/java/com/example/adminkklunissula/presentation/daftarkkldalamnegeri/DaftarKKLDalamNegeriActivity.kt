package com.example.adminkklunissula.presentation.daftarkkldalamnegeri

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminkklunissula.R
import com.example.adminkklunissula.databinding.ActivityDaftarKkldalamNegeriBinding

class DaftarKKLDalamNegeriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarKkldalamNegeriBinding
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


    }
}