package com.tahacan.yemekprojesi.ui.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tahacan.yemekprojesi.data.entity.Yemekler
import com.tahacan.yemekprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel() {

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var favoriYemeklerListesi = MutableLiveData<MutableList<Yemekler>>(mutableListOf())

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value = yemeklerRepository.yemekleriYukle()
        }
    }

    fun ara(aramaKelimesi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                if (aramaKelimesi.isBlank()) {
                    yemekleriYukle()
                } else {
                    val tumListe = yemeklerListesi.value ?: listOf()
                    val filtreliListe = tumListe.filter {
                        it.yemekAdi.contains(aramaKelimesi, ignoreCase = true)
                    }
                    yemeklerListesi.value = filtreliListe
                }
            } catch (e: Exception) {

            }
        }

    }
    fun favoriyeEkle(yemek: Yemekler, context: Context) {
        val mevcutListe = favoriYemeklerListesi.value ?: mutableListOf()
        if (!mevcutListe.contains(yemek)) {
            mevcutListe.add(yemek)
            favoriYemeklerListesi.value = mevcutListe
            Toast.makeText(context, "${yemek.yemekAdi} favorilere eklendi", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "${yemek.yemekAdi} zaten favorilerde", Toast.LENGTH_SHORT).show()
        }
    }

    fun favoridenSil(yemek: Yemekler) {
        val mevcutListe = favoriYemeklerListesi.value ?: mutableListOf()
        mevcutListe.remove(yemek)
        favoriYemeklerListesi.value = mevcutListe
    }


}