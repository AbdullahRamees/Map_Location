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
import com.atarious.map_location.Data.tables.City
import com.atarious.map_location.Data.viewModels.cityViewModel
import com.atarious.map_location.databinding.FragmentAddCityBinding


class addCityFragment : DialogFragment() {

    private var _binding: FragmentAddCityBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCityViewModel: cityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCityViewModel = ViewModelProvider(this).get(cityViewModel::class.java)
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        binding.idView2Add.text = (mCityViewModel.maxid+1).toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.SaveButton.setOnClickListener {
            addNewCity()
            this.dismiss()
        }
        binding.CloseButton.setOnClickListener {
            this.dismiss()
        }
    }

    private fun addNewCity() {
        val cityName = binding.editCityadd.text.toString()
        val countryName = binding.editCountryAdd.text.toString()
        val LatFloat = binding.editLatAdd.text.toString()
        val lngFloat = binding.lngEditAdd.text.toString()


        if(inputCheck(cityName,countryName,LatFloat,lngFloat)){
            addDataintodatabase(cityName,countryName,LatFloat.toFloat(),lngFloat.toFloat())
            val toast = Toast.makeText(context,"City Details added", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,140)
            toast.show()
        }else{
            val toast = Toast.makeText(context,"Please Input Empty Field", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,140)
            toast.show()
        }
    }

    //check if our input are empty
    private  fun inputCheck(city:String,country: String,lat: String,lng: String): Boolean {
        return !(TextUtils.isEmpty(country) && TextUtils.isEmpty(city) && TextUtils.isEmpty(lat) && TextUtils.isEmpty(lng))
    }

    //add a single user to database
    private fun addDataintodatabase(city: String, country: String, lat: Float, lng: Float) {
        val city = City(0,lat,lng,city,country)
        mCityViewModel.addCity(city)
    }
}