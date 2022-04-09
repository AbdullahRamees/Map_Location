package com.atarious.map_location.Data.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.atarious.map_location.Data.Repositories.CityRepository
import com.atarious.map_location.Data.database.LocalDatabase
import com.atarious.map_location.Data.tables.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class cityViewModel(application: Application):AndroidViewModel(application) {

    val allCity : LiveData<List<City>>
    val maxid : Int
    private val repository:CityRepository

    init{
        val citydao = LocalDatabase.getDatabase(application).cityDao()
        repository = CityRepository(citydao)
        allCity = repository.AllCity
        maxid = repository.maxId
    }

    fun addCity(city: City){
        viewModelScope.launch(Dispatchers.IO){
            repository.AddCity(city)
        }
    }
    fun updatecity(city: City){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateCity(city)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
    fun deleteCity(ID:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCity(ID)
        }
    }
    fun Clear(){
        viewModelScope.launch(Dispatchers.IO){
            repository.Clear()
        }
    }
}