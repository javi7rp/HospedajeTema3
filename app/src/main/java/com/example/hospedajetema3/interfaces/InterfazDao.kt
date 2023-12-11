package com.example.hospedajetema3.interfaces

import com.example.hospedajetema3.models.Hotel
import com.example.hospedajetema3.objects_models.Respository

interface InterfazDao {
    fun getDataHotels(): List<Hotel> = Respository.listHotel
}