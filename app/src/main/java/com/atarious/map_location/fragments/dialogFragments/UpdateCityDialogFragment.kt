package com.atarious.map_location.fragments.dialogFragments

import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.atarious.map_location.Data.tables.City
import com.atarious.map_location.Data.viewModels.cityViewModel
import com.atarious.map_location.databinding.FragmentUpdateCityDialogBinding


class UpdateCityDialogFragment : DialogFragment() {

    private var _binding: FragmentUpdateCityDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCityViewModel: cityViewModel
    private val args by navArgs<UpdateCityDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCityViewModel = ViewModelProvider(this).get(cityViewModel::class.java)
        _binding = FragmentUpdateCityDialogBinding.inflate(inflater, container, false)
        binding.editCity.setText(args.updateData?.city)
        binding.idView2.text = args.updateData?.id.toString()
        binding.editCountry.setText(args.updateData?.country)
        binding.editLat.setText(args.updateData?.lat.toString())
        binding.lngEdit.setText(args.updateData?.lng.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.SaveButton.setOnClickListener {
            updateData()
        }
        binding.CloseButton.setOnClickListener {
            this.dismiss()
        }
    }

    private fun updateData(){
        val id = args.updateData!!.id
        val cityname = binding.editCity.text.toString()
        val countryName = binding.editCountry.text.toString()
        val latfloat = binding.editLat.text.toString()
        val lngFloat = binding.lngEdit.text.toString()

        if(inputCheck(cityname,countryName,latfloat,lngFloat)){
            UpdateDataintodatabase(id,cityname,countryName,latfloat.toFloat(),lngFloat.toFloat())
            val toast = Toast.makeText(context,"update Done", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,140)
            toast.show()
            this.dismiss()
        }else{
            val toast = Toast.makeText(context,"Please Input Empty Field", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,140)
            toast.show()
        }
    }

    private  fun inputCheck(city:String,country: String,lat: String,lng: String): Boolean {
        return !(TextUtils.isEmpty(country) && TextUtils.isEmpty(city) && TextUtils.isEmpty(lat) && TextUtils.isEmpty(lng))

    }
    private fun UpdateDataintodatabase(id:Int,city: String, country: String, lat: Float, lng: Float) {
        val city = City(id,lat,lng,city,country)
        mCityViewModel.updatecity(city)
    }

}