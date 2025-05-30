package com.tahacan.yemekprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahacan.yemekprojesi.data.entity.SepetYemek
import com.tahacan.yemekprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(
    private val yemeklerRepository : YemeklerRepository ) : ViewModel() {

        val sepetListesi = MutableLiveData<List<SepetYemek>>()
        val toplamFiyat = MutableLiveData<Int>()
        private val _bosSepetMesaji = MutableLiveData<Boolean>()
        val bosSepetMesaji: MutableLiveData<Boolean> get() = _bosSepetMesaji
        init {
            sepetiYukle()
        }
    fun yemekSil(sepetYemekId: Int, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                yemeklerRepository.yemekSil(sepetYemekId, kullaniciAdi)
                // Silme işlemi başarılı olduğunda sepeti yeniden yükle
                sepetiYukle()
            } catch (e: Exception) {
                Log.e("yemekSil", "Silme hatası: ${e.localizedMessage}")
                // Hata durumunda da sepeti yeniden yükle (güncel durumu görmek için)
                sepetiYukle()
            }
        }
    }

    fun sepetiYukle() {
        viewModelScope.launch {
            try {
                val sepet = yemeklerRepository.sepetiYukle()
                sepetListesi.value = sepet

                val toplam = sepet.sumOf { it.yemekFiyat * it.yemekSiparisAdet }
                toplamFiyat.value = toplam

                _bosSepetMesaji.value = sepet.isEmpty()
            } catch (e: Exception) {
                Log.e("sepetiYukle", "Sepet yükleme hatası: ${e.localizedMessage}")
                // Hata durumunda boş liste göster
                sepetListesi.value = emptyList()
                toplamFiyat.value = 0
                _bosSepetMesaji.value = true
            }
        }

    }
}