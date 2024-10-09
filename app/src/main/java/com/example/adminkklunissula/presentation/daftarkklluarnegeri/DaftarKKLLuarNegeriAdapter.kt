package com.example.adminkklunissula.presentation.daftarkklluarnegeri

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkklunissula.data.model.DaftarKKLLuarNegeri
import com.example.adminkklunissula.databinding.KklluarNegeriItemBinding

class DaftarKKLLuarNegeriAdapter(
    private val daftarKKLList: List<DaftarKKLLuarNegeri>
) : RecyclerView.Adapter<DaftarKKLLuarNegeriAdapter.DaftarKKLViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: DaftarKKLLuarNegeri)
    }

    internal fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

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

    inner class DaftarKKLViewHolder(private val binding: KklluarNegeriItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DaftarKKLLuarNegeri) {
            binding.tvName.text = data.nama

            if(data.status == "1"){
                binding.tvStatus.text = "Under Reviewed"
                binding.tvStatus.setTextColor(Color.YELLOW)
            } else if (data.status == "2"){
                binding.tvStatus.text = "Accepted"
                binding.tvStatus.setTextColor(Color.GREEN)
            } else if (data.status == "3"){
                binding.tvStatus.text = "Rejected"
                binding.tvStatus.setTextColor(Color.RED)
            }

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

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(currentData)
            }
        }
    }

    override fun getItemCount(): Int {
        return daftarKKLList.size
    }
}