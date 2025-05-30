package com.tahacan.yemekprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahacan.yemekprojesi.R
import com.tahacan.yemekprojesi.data.entity.SepetYemek
import com.tahacan.yemekprojesi.databinding.ItemSiparisBinding

class SiparisAdapter(
    private val context: Context,
    private val urunler: List<SepetYemek>
) : RecyclerView.Adapter<SiparisAdapter.SiparisViewHolder>() {

    inner class SiparisViewHolder(val binding: ItemSiparisBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiparisViewHolder {
        val binding = ItemSiparisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SiparisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SiparisViewHolder, position: Int) {
        val urun = urunler[position]
        holder.binding.tvAdSoyad.text = urun.yemekAdi
        holder.binding.tvyemekBilgi.text = "${urun.yemekSiparisAdet} x ₺${urun.yemekFiyat}"
        val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemekResimAdi}"
        Glide.with(context)
            .load(resimUrl)
            .into(holder.binding.yemekResim)

        val adet = urun.yemekSiparisAdet
        val fiyat = urun.yemekFiyat
        val toplam = adet * fiyat
        holder.binding.tvToplamFiyat.text = "$adet x ₺$fiyat = ₺$toplam"

    }

    override fun getItemCount(): Int = urunler.size
}