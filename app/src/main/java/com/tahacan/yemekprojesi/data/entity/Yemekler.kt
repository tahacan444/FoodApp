package com.tahacan.yemekprojesi.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Yemekler(
    @SerializedName("yemek_id") val yemekId: Int,
    @SerializedName("yemek_adi") val yemekAdi: String,
    @SerializedName("yemek_resim_adi") val yemekResimAdi: String,
    @SerializedName("yemek_fiyat") val yemekFiyat: Int

) : Serializable