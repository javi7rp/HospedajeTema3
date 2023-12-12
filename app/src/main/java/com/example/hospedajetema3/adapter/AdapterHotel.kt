package com.example.hospedajetema3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.R
import com.example.hospedajetema3.models.Hotel

class AdapterHotel(
    var listHotel : MutableList<Hotel>,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit
    ) : RecyclerView.Adapter<ViewHHotel>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHHotel {
        val layoutInflater = LayoutInflater.from(parent. context)
        val layoutItemHotel = R.layout.recycler_view
        return ViewHHotel(
            layoutInflater.inflate(layoutItemHotel, parent, false),
            deleteOnClick,
            updateOnClick
        )
    }

    override fun onBindViewHolder(holder: ViewHHotel, position: Int) {
        holder.renderize( listHotel.get(position)) //renderizamos la view.
        holder.setOnClickListener(position)
    }
    override fun getItemCount(): Int = listHotel.size
}