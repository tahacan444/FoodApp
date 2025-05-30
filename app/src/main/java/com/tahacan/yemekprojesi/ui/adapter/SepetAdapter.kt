package com.tahacan.yemekprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahacan.yemekprojesi.data.entity.SepetYemek
import com.tahacan.yemekprojesi.databinding.ItemSepetBinding
import com.tahacan.yemekprojesi.ui.viewmodel.SepetViewModel

class SepetAdapter (
    private val context: Context,
    private var sepetList: List<SepetYemek>,
    private val viewModel:SepetViewModel
):RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(val binding: ItemSepetBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
         val layoutInflater = LayoutInflater.from(context)
         val binding = ItemSepetBinding.inflate(layoutInflater,parent,false)
         return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val item = sepetList[position]
        holder.binding.sepetYemekAd.text = item.yemekAdi
        holder.binding.sepetYemekFiyat.text = "${item.yemekFiyat} ₺ x ${item.yemekSiparisAdet}"

        var toplam = item.yemekFiyat.toInt() * item.yemekSiparisAdet.toInt()
        holder.binding.toplamFiyat.text = "Toplam: $toplam ₺"

        val imgUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemekResimAdi}"
        Glide.with(context).load(imgUrl).override(500,400).into(holder.binding.sepetYemekResim)

        holder.binding.sepetSil.setOnClickListener {
           viewModel.yemekSil(item.sepetYemekId,item.kullaniciAdi)
        }
    }
    override fun getItemCount(): Int = sepetList.size

    fun updateList(newList: List<SepetYemek>) {
        sepetList = newList
        notifyDataSetChanged()
    }

}