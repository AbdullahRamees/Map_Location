package com.atarious.map_location.Data.tables



import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "city_table")
data class City (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "latitude")val lat :Float,
    @ColumnInfo(name = "longitude")val lng :Float,
    @ColumnInfo(name = "city")val city:String,
    @ColumnInfo(name = "country")val country:String
        ):Parcelable
