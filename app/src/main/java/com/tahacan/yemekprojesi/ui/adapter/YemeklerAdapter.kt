package com.tahacan.yemekprojesi.ui.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahacan.yemekprojesi.data.entity.Yemekler
import com.tahacan.yemekprojesi.databinding.ItemProductBinding
import com.tahacan.yemekprojesi.ui.fragment.AnasayfaFragmentDirections
import com.tahacan.yemekprojesi.ui.viewmodel.AnasayfaViewModel
import com.tahacan.yemekprojesi.utils.gecisYap

class YemeklerAdapter(var mContext : Context,
                      var yemeklerListesi : List<Yemekler>,
                      var viewModel : AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.YemekViewHolder>() {

    inner class YemekViewHolder(var tasarim: ItemProductBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val tasarim = ItemProductBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return YemekViewHolder(tasarim)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        val yemek = yemeklerListesi[position]
        holder.tasarim.apply {
            productName.text = yemek.yemekAdi
            productPrice.text = "₺ ${yemek.yemekFiyat}"
            Glide.with(imageViewYemekler.context)
                .load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemekResimAdi}")
                .override(500,500)
                .into(imageViewYemekler)
        }
        val t = holder.tasarim

        t.cardViewSatir.setOnClickListener {
               val gecis = AnasayfaFragmentDirections.gecisDetay(yemek = yemek)
               Navigation.gecisYap(it,gecis)
        }

        t.imgbtFavorite.setOnClickListener {
                val gecis = AnasayfaFragmentDirections.gecisFavori(yemek)
                Navigation.findNavController(it).navigate(gecis)

        }

    }



    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    fun updateList(yeniListe: List<Yemekler>) {
        yemeklerListesi = yeniListe
        notifyDataSetChanged()
    }




}