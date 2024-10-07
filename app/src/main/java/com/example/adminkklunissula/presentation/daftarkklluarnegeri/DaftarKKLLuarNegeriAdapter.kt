package com.example.adminkklunissula.presentation.daftarkklluarnegeri

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.databinding.KklluarNegeriItemBinding

class DaftarKKLLuarNegeriAdapter(
    private val daftarKKLList: List<DaftarKKLLuarNegeri>
) : RecyclerView.Adapter<DaftarKKLLuarNegeriAdapter.DaftarKKLViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarKKLViewHolder {
        val binding =KklluarNegeriItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaftarKKLViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaftarKKLViewHolder, position: Int) {
        val data = daftarKKLList[position]
        if (data != null) {
            holder.bind(data)
        }
    }

    class DaftarKKLViewHolder(private val binding: KklluarNegeriItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DaftarKKLLuarNegeri) {
            binding.tvName.text = data.nama

            val currentData = DaftarKKLLuarNegeri(
                buktiUrl = data.buktiUrl,
                email = data.email,
                fotoUrl = data.fotoUrl,
                jenisKelamin = data.jenisKelamin,
                ktpUrl = data.ktpUrl,
                nama = data.nama,
                nim = data.nim,
                noHp = data.noHp,
                smtKelas = data.smtKelas,
                documentId = data.documentId,
                kotaBerangkat = data.kotaBerangkat,
                kotaPulang = data.kotaPulang,
                pasporUrl = data.pasporUrl
            )
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailKKLLuarNegeriActivity::class.java)
                intent.putExtra("data", currentData)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return daftarKKLList.size
    }
}