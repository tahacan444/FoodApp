package com.tahacan.yemekprojesi.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahacan.yemekprojesi.data.entity.Yemekler
import com.tahacan.yemekprojesi.databinding.ItemFavoriteBinding
import com.tahacan.yemekprojesi.databinding.ItemProductBinding
import com.tahacan.yemekprojesi.ui.fragment.AnasayfaFragmentDirections
import com.tahacan.yemekprojesi.ui.viewmodel.AnasayfaViewModel
import com.tahacan.yemekprojesi.utils.gecisYap

class FavorilerAdapter (
    private val context: Context,
    private var favoriList: MutableList<Yemekler>,
    private val viewModel: AnasayfaViewModel
) : RecyclerView.Adapter<FavorilerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val yemek = favoriList[position]
        val t = holder.binding

        t.favYemekAdi.text = yemek.yemekAdi
        t.favYemekFiyat.text = "₺ ${yemek.yemekFiyat}"

        Glide.with(context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemekResimAdi}")
            .into(t.favYemekImage)

        t.favYemekImage.setOnClickListener {
            viewModel.favoridenSil(yemek)
        }
        t.favYemekDelete.setOnClickListener {
            viewModel.favoridenSil(yemek)
            val yeniListe = viewModel.favoriYemeklerListesi.value ?: listOf()
            updateList(yeniListe)
            Toast.makeText(context, "${yemek.yemekAdi} favorilerden silindi", Toast.LENGTH_SHORT).show()
        }

    }

     override fun getItemCount(): Int = favoriList.size

        fun updateList(newList: List<Yemekler>) {
            favoriList.clear()
            favoriList.addAll(newList)
            notifyDataSetChanged()
        }
    }