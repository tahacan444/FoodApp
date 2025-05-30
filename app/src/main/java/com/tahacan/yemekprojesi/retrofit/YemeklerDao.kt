package com.tahacan.yemekprojesi.retrofit

import com.tahacan.yemekprojesi.data.entity.CRUDCevap
import com.tahacan.yemekprojesi.data.entity.SepetYemekCevap
import com.tahacan.yemekprojesi.data.entity.Yemekler
import retrofit2.http.Field
import com.tahacan.yemekprojesi.data.entity.YemeklerCevap
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun  yemekleriYukle(): YemeklerCevap

     @POST("yemekler/sepeteYemekEkle.php")
     @FormUrlEncoded
     suspend fun sepeteEkle(
         @Field("yemek_adi") yemekAdi: String,
         @Field("yemek_resim_adi") yemekResim: String,
         @Field("yemek_fiyat") yemekFiyat: Int,
         @Field("yemek_siparis_adet") yemekAdet: Int,
         @Field("kullanici_adi") kullaniciAdi: String
     ) : CRUDCevap

     @POST("yemekler/sepettekiYemekleriGetir.php")
     @FormUrlEncoded
     suspend fun sepetiYukle(
         @Field("kullanici_adi") kullaniciAdi: String

     ) :SepetYemekCevap


     @POST("yemekler/sepettenYemekSil.php")
     @FormUrlEncoded
     suspend fun  yemekSil(
         @Field("sepet_yemek_id") sepetYemekId: Int,
         @Field("kullanici_adi") kullaniciAdi: String
     ) :  CRUDCevap



}