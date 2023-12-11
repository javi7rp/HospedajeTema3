package com.example.hospedajetema3.dao

import com.example.hospedajetema3.models.Hotel
import com.example.hospedajetema3.objects_models.Respository
object DaoHotels2 {
    val myDao by lazy {
        getDataHotels()
    }
    private fun getDataHotels() : List<Hotel> = Respository.listHotel
}