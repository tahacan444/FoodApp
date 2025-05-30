package com.tahacan.yemekprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tahacan.yemekprojesi.R
import com.tahacan.yemekprojesi.databinding.FragmentSepetBinding
import com.tahacan.yemekprojesi.databinding.ItemProductBinding
import com.tahacan.yemekprojesi.ui.adapter.SepetAdapter
import com.tahacan.yemekprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private val viewModel: SepetViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater,container,false)

        val adapter = SepetAdapter(requireContext(), emptyList(),viewModel)
        binding.cardList.adapter = adapter
        binding.cardList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.sepetListesi.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
        viewModel.bosSepetMesaji.observe(viewLifecycleOwner) { bos ->
            if (bos) {
                Toast.makeText(requireContext(), "Sepette ürün yok.", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.toplamFiyat.observe(viewLifecycleOwner) { toplam ->
            binding.cartTotal.text = "Toplam: ₺$toplam"
        }

        // Sepet boşsa uyarı göster
        viewModel.bosSepetMesaji.observe(viewLifecycleOwner) { bosMu ->
            if (bosMu) {
                Toast.makeText(requireContext(), "Sepette ürün yok", Toast.LENGTH_SHORT).show()
                binding.cartTotal.text = "Toplam: ₺0"
            }
        }
        binding.btnOdemeYap.setOnClickListener {
            val sepetList = viewModel.sepetListesi.value ?: emptyList()
            val toplamFiyat = viewModel.toplamFiyat.value ?: 0

            if (sepetList.isEmpty()) {
                Toast.makeText(requireContext(), "Sepet boş", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val urunBilgileri = sepetList.map {
                "${it.yemekAdi} - ${it.yemekSiparisAdet} x ${it.yemekFiyat}"
            }

            Toast.makeText(requireContext(), "Siparişiniz hazırlanıyor", Toast.LENGTH_SHORT).show()

            val bundle = Bundle().apply {
                putSerializable("urunler", ArrayList(sepetList))
                putInt("toplamFiyat", toplamFiyat)
            }

            findNavController().navigate(R.id.siparisGecis, bundle)
        }




        viewModel.sepetiYukle()

        return binding.root
    }


    }
