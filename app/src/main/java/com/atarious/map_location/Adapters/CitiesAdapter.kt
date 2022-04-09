package com.atarious.map_location.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.atarious.map_location.Data.tables.City
import com.atarious.map_location.R
import com.atarious.map_location.fragments.List.ListFragmentDirections

class CitiesAdapter() :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    private var cityList = emptyList<City>()

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
     override fun onBindViewHolder(holder: ViewHolder, position: Int ,) {
        val itemsViewModel = cityList[position]
        holder.textViewCity.text="${itemsViewModel.city} , ${itemsViewModel.country}"
        holder.viewLocation.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToMapFragment(itemsViewModel)
            holder.itemView.findNavController().navigate(action)
        }
        holder.EditButton.setOnClickListener {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(itemsViewModel)
            holder.itemView.findNavController().navigate(action)
        }
        holder.DeleteButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDeletedata(itemsViewModel)
            holder.itemView.findNavController().navigate(action)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return cityList.size
    }

    // Holds the views for adding it to image and text

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewCity: TextView = itemView.findViewById(R.id.CityName)
        val EditButton: ImageButton = itemView.findViewById(R.id.EditButton)
        val DeleteButton: ImageButton = itemView.findViewById(R.id.DeleteButton)
        val viewLocation : LinearLayout = itemView.findViewById(R.id.cityLayout)
    }

    fun SetData(city:List<City>){
        this.cityList = city
        notifyDataSetChanged()
    }
}
