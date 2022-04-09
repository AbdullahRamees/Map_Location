package com.atarious.map_location.Data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.atarious.map_location.Data.tables.City


@Dao
interface CityDao {

    @Query("SELECT * FROM city_table ORDER BY id ASC")
    fun ReadAllCity():LiveData<List<City>>

    @Query("SELECT max(id) FROM city_table")
    fun maxid():Int

    @Query("DELETE FROM city_table WHERE id =:id")
    suspend fun deletCity(id:Int)

    @Query("DELETE  FROM city_table")
    suspend fun deleteAll()

    @Query("delete from sqlite_sequence where name='city_table'")
    suspend fun clear()
    @Update
    suspend fun updateCity(city: City)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(city:City)

}