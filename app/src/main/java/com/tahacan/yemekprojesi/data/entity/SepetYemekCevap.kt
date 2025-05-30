package com.tahacan.yemekprojesi.data.entity
import com.google.gson.annotations.SerializedName

data class SepetYemekCevap (
    @SerializedName("sepet_yemekler") var sepetYemekler: List<SepetYemek>,
    @SerializedName("success") val success: String
)