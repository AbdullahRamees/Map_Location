package com.atarious.map_location.fragments.List

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atarious.map_location.Adapters.CitiesAdapter
import com.atarious.map_location.Data.tables.City
import com.atarious.map_location.Data.viewModels.cityViewModel
import com.atarious.map_location.R
import com.atarious.map_location.api.ApiServices
import com.atarious.map_location.databinding.FragmentListBinding
import com.atarious.map_location.model.Cities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    //toGetSampledata
    private val ApiServicesOBJ = ApiServices.create()

    //floating button Animation
    private val rotateOpen:Animation by lazy{AnimationUtils.loadAnimation(this.context,R.anim.rotate_open_anim)}
    private val rotateClose:Animation by lazy{AnimationUtils.loadAnimation(this.context,R.anim.rotate_close_anim)}
    private val fromBottom:Animation by lazy{AnimationUtils.loadAnimation(this.context,R.anim.from_bottom_anim)}
    private val toBottom:Animation by lazy{AnimationUtils.loadAnimation(this.context,R.anim.to_bottom_anim)}
    //check is Add floating button clicked
    private var clicked:Boolean = false

    //databas View model
    private lateinit var mCityViewModel: cityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //create instance
        mCityViewModel = ViewModelProvider(this).get(cityViewModel::class.java)
        activity?.findViewById<TextView>(R.id.custom_title)?.text = "City List"
        _binding = FragmentListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun deleteallcites() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->
            mCityViewModel.deleteAll()
            mCityViewModel.Clear()
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("delete all cities")
        builder.setMessage("Are you sure you want to delete all city details from database?")
        builder.create().show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteAlldeta.setOnClickListener {
            deleteallcites()
        }
        //what happen when we clicked add data from api button
        binding.fromData.setOnClickListener{
            AddDataFromApi()
        }
        //what happen when we click Add floating button
        binding.add.setOnClickListener {
            onAddButtonClicked()
        }
        //add data to recycle view
        val adapter = CitiesAdapter()
        binding.recyclerview.adapter = adapter
        mCityViewModel.allCity.observe(viewLifecycleOwner, { City ->
            adapter.SetData(City)
        })
        //what happen if we clicked add new Location
        binding.NewData.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_addcity)

        }
        binding.recyclerview.layoutManager = LinearLayoutManager(view.context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Functions

    //action when button Add clicked
    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    //set button animation
    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            binding.add.startAnimation(rotateOpen)
            binding.NewData.startAnimation(fromBottom)
            binding.deleteAlldeta.startAnimation(fromBottom)
            binding.newDataText.startAnimation(fromBottom)
            binding.fromData.startAnimation(fromBottom)
            binding.fromDataText.startAnimation(fromBottom)
        }else{
            binding.add.startAnimation(rotateClose)
            binding.NewData.startAnimation(toBottom)
            binding.deleteAlldeta.startAnimation(toBottom)
            binding.newDataText.startAnimation(toBottom)
            binding.fromData.startAnimation(toBottom)
            binding.fromDataText.startAnimation(toBottom)
        }
    }

    //set button visibility
    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.fromData.visibility = View.VISIBLE
            binding.fromDataText.visibility = View.VISIBLE
            binding.NewData.visibility = View.VISIBLE
            binding.newDataText.visibility = View.VISIBLE
            binding.deleteAlldeta.visibility = View.VISIBLE

        }else{
            binding.NewData.visibility = View.INVISIBLE
            binding.newDataText.visibility = View.INVISIBLE
            binding.deleteAlldeta.visibility = View.INVISIBLE
            binding.fromData.visibility = View.INVISIBLE
            binding.fromDataText.visibility = View.INVISIBLE
        }
    }

    //adding data from api
    private fun AddDataFromApi(){
        val CitiesOBJ = ApiServicesOBJ.getCities()
        CitiesOBJ.enqueue(object : Callback<List<Cities>> {
            override fun onResponse(
                call: Call<List<Cities>>,
                response: Response<List<Cities>>
            ) {
                mCityViewModel.maxid
                val CityBody = response.body()
                    CityBody?.forEach {
                        addDataintodatabase(it.id.toInt(),it.city,it.country,it.lat,it.lng)
                    }
                val toast = Toast.makeText(context,"only 300 data Can be add...!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP,0,140)
                toast.show()
            }
            override fun onFailure(call: Call<List<Cities>>, t: Throwable) {
                val toast = Toast.makeText(context, t.message!!, Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP,0,140)
                toast.show()
            }

        })
    }
    //add a single user to database
    private fun addDataintodatabase(id:Int,city: String, country: String, lat: Float, lng: Float) {
        val cityData = City(id,lat,lng,city,country)
        mCityViewModel.addCity(cityData)
    }


}