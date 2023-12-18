package com.example.hospedajetema3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.R
import com.example.hospedajetema3.models.Juego

class AdapterJuego(
    var listJuego : MutableList<Juego>,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit
    ) : RecyclerView.Adapter<ViewJuego>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewJuego {
        val layoutInflater = LayoutInflater.from(parent. context)
        val layoutItemHotel = R.layout.recycler_view
        return ViewJuego(
            layoutInflater.inflate(layoutItemHotel, parent, false),
            deleteOnClick,
            updateOnClick
        )
    }

    override fun onBindViewHolder(holder: ViewJuego, position: Int) {
        val juego = listJuego[position]
        holder.renderize(juego, holder.itemView.context)
        holder.setOnClickListener(position)
    }
    override fun getItemCount(): Int = listJuego.size
}