package com.tahacan.yemekprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahacan.yemekprojesi.R
import com.tahacan.yemekprojesi.databinding.FragmentAnasayfaBinding
import com.tahacan.yemekprojesi.databinding.FragmentDetayBinding
import com.tahacan.yemekprojesi.ui.viewmodel.DetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private  lateinit var binding: FragmentDetayBinding
    private lateinit var  viewModel: DetayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetayBinding.inflate(inflater,container,false)
        val bundle: DetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        binding.priceText.text = "${gelenYemek.yemekFiyat} ₺"
        binding.productName.text = gelenYemek.yemekAdi

        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemekResimAdi}"

        Glide.with(requireContext())
            .load(imageUrl)
            .centerInside()
            .into(binding.productImage)

        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            val message = when (rating.toInt()) {
                1 -> "Çok Kötü"
                2 -> "Kötü"
                3 -> "Orta"
                4 -> "İyi"
                5 -> "Çok İyi"
                else -> "Puan yok"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        var miktar = 1

        binding.quantityText.text = miktar.toString()

        binding.increaseBtn.setOnClickListener{
            miktar++
            binding.quantityText.text = miktar.toString()
        }
        binding.decreaseBtn.setOnClickListener{
             if(miktar > 1 ) {
                 miktar--
                 binding.quantityText.text = miktar.toString()
             }
        }

        binding.addToCartBtn.setOnClickListener {
            val toplamFiyat = gelenYemek.yemekFiyat * miktar

            viewModel.sepeteEkle(
                yemekAdi = gelenYemek.yemekAdi,
                yemekResim = gelenYemek.yemekResimAdi,
                yemekFiyat = gelenYemek.yemekFiyat,
                yemekAdet = miktar
            )

            Toast.makeText(
                requireContext(),
                "$miktar adet ${gelenYemek.yemekAdi} sepete eklendi. Toplam: ₺$toplamFiyat",
                Toast.LENGTH_SHORT
            ).show()

        }





        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel
    }


}
