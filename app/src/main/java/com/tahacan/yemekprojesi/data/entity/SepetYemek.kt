package com.tahacan.yemekprojesi.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SepetYemek (
        @SerializedName("sepet_yemek_id") val sepetYemekId: Int,
        @SerializedName("yemek_adi") val yemekAdi: String,
        @SerializedName("yemek_resim_adi") val yemekResimAdi: String,
        @SerializedName("yemek_fiyat") val yemekFiyat: Int,
        @SerializedName("yemek_siparis_adet") val yemekSiparisAdet: Int,
        @SerializedName("kullanici_adi") val kullaniciAdi: String
):Serializable