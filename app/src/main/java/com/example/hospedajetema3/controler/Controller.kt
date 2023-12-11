package com.example.hospedajetema3.controler

import android.content.Context
import android.widget.Toast
import com.example.hospedajetema3.MainActivity
import com.example.hospedajetema3.adapter.AdapterHotel
import com.example.hospedajetema3.dao.DaoHotels2
import com.example.hospedajetema3.models.Hotel

class Controller ( val context : Context){
    lateinit var listHotels : MutableList<Hotel> //lista de objetos
    init {
        initData()
    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        listHotels = DaoHotels2.myDao.toMutableList() //llamamos al singleton.
    }
    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listHotels.forEach{
            println (it)
        }
    }
    fun setAdapter() { // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
        val myActivity = context as MainActivity
        myActivity.binding.myRecyclerView.adapter = AdapterHotel(listHotels,{pos -> deleteHotel(pos)},{pos -> updateHotel(pos)})
    }

    fun deleteHotel(pos:Int){
        Toast.makeText(context, "Borramos el hotel $pos",Toast.LENGTH_LONG).show()
        listHotels.removeAt(pos)

    }
}