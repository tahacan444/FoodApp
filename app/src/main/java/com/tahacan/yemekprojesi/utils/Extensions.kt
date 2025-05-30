package com.tahacan.yemekprojesi.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.tahacan.yemekprojesi.R

fun Navigation.gecisYap(it: View,id:Int){
    findNavController(it).navigate(id)
}

fun Navigation.gecisYap(it: View,id: NavDirections){
    findNavController(it).navigate(id)
}