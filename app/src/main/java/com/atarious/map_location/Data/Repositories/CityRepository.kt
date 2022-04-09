package com.atarious.map_location.Data.Repositories

import androidx.lifecycle.LiveData
import com.atarious.map_location.Data.dao.CityDao
import com.atarious.map_location.Data.tables.City

class CityRepository(private val  cityDao: CityDao) {
    val AllCity:LiveData<List<City>> = cityDao.ReadAllCity()
    val maxId:Int = cityDao.maxid()

    suspend fun AddCity(city: City){
        cityDao.addCity(city)
    }
    suspend fun updateCity(city: City){
        cityDao.updateCity(city)
    }
    suspend fun deleteCity(ID:Int){
        cityDao.deletCity(ID)
    }
    suspend fun deleteAll(){
        cityDao.deleteAll()
    }
    suspend fun Clear(){
        cityDao.clear()
    }
}