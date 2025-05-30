package com.tahacan.yemekprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahacan.yemekprojesi.R
import com.tahacan.yemekprojesi.data.entity.Yemekler
import com.tahacan.yemekprojesi.databinding.FragmentFavorilerBinding
import com.tahacan.yemekprojesi.ui.adapter.FavorilerAdapter
import com.tahacan.yemekprojesi.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorilerFragment : Fragment() {
    private lateinit var binding: FragmentFavorilerBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavorilerBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(requireActivity())[AnasayfaViewModel::class.java]

        val favoriAdapter = FavorilerAdapter(requireContext(), mutableListOf(), viewModel)

        binding.favoritesRecyclerView.adapter = favoriAdapter
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        arguments?.let {
            val favoriYemek = it.getSerializable("yemek") as? Yemekler
            favoriYemek?.let { yemek ->
                viewModel.favoriyeEkle(yemek, requireContext())
            }
        }

        viewModel.favoriYemeklerListesi.observe(viewLifecycleOwner) {
            favoriAdapter.updateList(it)
        }





        return binding.root
    }
}