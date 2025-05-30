package com.tahacan.yemekprojesi.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tahacan.yemekprojesi.databinding.FragmentSiparisBinding
import com.tahacan.yemekprojesi.ui.adapter.SiparisAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahacan.yemekprojesi.data.entity.SepetYemek


class SiparisFragment : Fragment() {
    private lateinit var binding:FragmentSiparisBinding
    private lateinit var urunler: ArrayList<SepetYemek>

    private var toplamFiyat: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSiparisBinding.inflate(inflater,container,false)

        arguments?.let { bundle ->
            val urunler = bundle.getSerializable("urunler") as? ArrayList<SepetYemek> ?: arrayListOf()
            val toplamFiyat = bundle.getInt("toplamFiyat", 0)
        }


        val bundle = arguments
        if (bundle != null) {
            urunler = bundle.getSerializable("urunler") as? ArrayList<SepetYemek> ?: arrayListOf()
            toplamFiyat = bundle.getInt("toplamFiyat", 0)
        } else {
            urunler = arrayListOf()
            toplamFiyat = 0
        }


        val siparisAdapter = SiparisAdapter(requireContext(), urunler)
        binding.siparisRv.apply {
            adapter = siparisAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        binding.tvOdemeOzeti.text = "Toplam: ₺$toplamFiyat"


        binding.btnSiparisOde.setOnClickListener {
            val adSoyad = binding.etAdSoyad.text.toString()
            val adres = binding.etAdres.text.toString()
            val telefon = binding.etTelefon.text.toString()

            if (adSoyad.isBlank() || adres.isBlank() || telefon.isBlank()) {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Ödeme başarılı. Teşekkür ederiz!", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}