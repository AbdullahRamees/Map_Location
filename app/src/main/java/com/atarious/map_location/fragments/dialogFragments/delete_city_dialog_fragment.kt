package com.atarious.map_location.fragments.dialogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.atarious.map_location.Data.viewModels.cityViewModel
import com.atarious.map_location.databinding.FragmentDeleteCityDialogBinding


class delete_city_dialog_fragment : DialogFragment() {
    private var _binding: FragmentDeleteCityDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCityViewModel: cityViewModel
    private val args by navArgs<delete_city_dialog_fragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mCityViewModel = ViewModelProvider(this).get(cityViewModel::class.java)
        _binding = FragmentDeleteCityDialogBinding.inflate(inflater, container, false)
        binding.titleVieww.text = "Are you Sure You want to delete ${args.deletedata!!.city} ,${args.deletedata!!.country} ?"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yes.setOnClickListener {
            deleteData()
            this.dismiss()
        }
        binding.no.setOnClickListener {
            this.dismiss()
        }
    }

    private fun deleteData() {
        val id = args.deletedata!!.id
        mCityViewModel.deleteCity(id)

    }

}