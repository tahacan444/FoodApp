package com.tahacan.yemekprojesi.data.datasource

import com.tahacan.yemekprojesi.data.entity.CRUDCevap
import com.tahacan.yemekprojesi.data.entity.SepetYemek
import com.tahacan.yemekprojesi.data.entity.Yemekler
import com.tahacan.yemekprojesi.retrofit.YemeklerDao
import com.tahacan.yemekprojesi.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var yemeklerDao: YemeklerDao) {
    suspend fun sepeteEkle( yemekAdi: String,
                            yemekResim: String,
                            yemekFiyat: Int,
                            yemekAdet: Int,
                            kullaniciAdi: String = Constants.KULLANICI_ADI) {
        val crudCveap = yemeklerDao.sepeteEkle(yemekAdi,yemekResim,yemekAdet,yemekFiyat,kullaniciAdi)
    }
    suspend fun sepetiYukle( kullaniciAdi: String) : List<SepetYemek> = withContext(Dispatchers.IO)  {
        return@withContext yemeklerDao.sepetiYukle(kullaniciAdi).sepetYemekler
    }

    suspend fun yemekleriYukle() : List<Yemekler>  = withContext(Dispatchers.IO){
        return@withContext yemeklerDao.yemekleriYukle().yemekler
    }

    suspend fun yemekSil(sepetYemekId: Int, kullaniciAdi: String): CRUDCevap {
        return yemeklerDao.yemekSil(sepetYemekId, kullaniciAdi)
    }


}

