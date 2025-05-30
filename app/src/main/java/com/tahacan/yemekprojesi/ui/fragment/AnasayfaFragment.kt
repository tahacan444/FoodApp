package com.tahacan.yemekprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tahacan.yemekprojesi.R
import com.tahacan.yemekprojesi.databinding.FragmentAnasayfaBinding
import com.tahacan.yemekprojesi.ui.adapter.YemeklerAdapter
import com.tahacan.yemekprojesi.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)

         viewModel = ViewModelProvider(this) [AnasayfaViewModel::class.java]


        val yemeklerAdapter = YemeklerAdapter(requireContext(), emptyList(), viewModel)
        binding.yemeklerView.adapter = yemeklerAdapter

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            yemeklerAdapter.updateList(it)
        }
        binding.yemeklerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {//Ara buttonuna basılınca
                viewModel.ara(query)
                return true
            }
        })



        return binding.root
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }
}