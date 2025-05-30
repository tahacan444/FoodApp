package com.tahacan.yemekprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tahacan.yemekprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel()  {

    fun sepeteEkle(
        yemekAdi: String,
        yemekResim: String,
        yemekFiyat: Int,
        yemekAdet: Int
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerRepository.sepeteEkle(yemekAdi, yemekResim, yemekFiyat, yemekAdet)
        }
    }




}