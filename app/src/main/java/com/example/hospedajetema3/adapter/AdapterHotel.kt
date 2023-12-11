package com.example.hospedajetema3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.R
import com.example.hospedajetema3.models.Hotel

class AdapterHotel( var listHotel : MutableList<Hotel>) : RecyclerView.Adapter<ViewHHotel>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHHotel {
        val layoutInflater = LayoutInflater.from(parent. context)//objeto para crear la vista.
        val layoutItemHotel = R.layout.recycler_view //accedo al xml del item a crear.
        return ViewHHotel(layoutInflater.inflate(layoutItemHotel, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHHotel, position: Int) {
        holder.renderize( listHotel.get(position)) //renderizamos la view.
    }
    override fun getItemCount(): Int = listHotel.size
}