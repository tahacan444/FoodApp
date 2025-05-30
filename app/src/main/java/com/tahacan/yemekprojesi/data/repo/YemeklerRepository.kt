package com.tahacan.yemekprojesi.data.repo

import com.tahacan.yemekprojesi.data.datasource.YemeklerDataSource
import com.tahacan.yemekprojesi.data.entity.SepetYemek
import com.tahacan.yemekprojesi.data.entity.Yemekler
import com.tahacan.yemekprojesi.utils.Constants

class YemeklerRepository(var yemeklerDataSource: YemeklerDataSource) {

    suspend fun yemekleriYukle() : List<Yemekler> = yemeklerDataSource.yemekleriYukle()

    suspend fun sepeteEkle(yemekAdi: String, yemekResim: String, yemekFiyat: Int, yemekAdet: Int) {
        yemeklerDataSource.sepeteEkle(yemekAdi,yemekResim,yemekFiyat,yemekAdet,Constants.KULLANICI_ADI)
    }
    suspend fun sepetiYukle() : List<SepetYemek> {
        return yemeklerDataSource.sepetiYukle(Constants.KULLANICI_ADI)
    }
    suspend fun yemekSil(sepetYemekId : Int,kullaniciAdi:String)  = yemeklerDataSource.yemekSil(sepetYemekId,kullaniciAdi)
}