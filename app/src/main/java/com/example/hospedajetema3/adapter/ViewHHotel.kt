package com.example.hospedajetema3.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.models.Hotel
import com.bumptech.glide.Glide
import com.example.hospedajetema3.databinding.ItemHotelBinding
class ViewHHotel (view: View) : RecyclerView.ViewHolder (view){
    lateinit var binding: ItemHotelBinding
    init {
        binding = ItemHotelBinding.bind(view)
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(hotel : Hotel){
        binding.txtviewName.setText(hotel.name)
        binding.txtviewCity.setText(hotel.city)
        binding.txtviewProvince.setText(hotel.province)
        binding.txtviewPhone.setText(hotel.phone)
        binding.ivHotel.loadImageWithGlide(hotel.image)
    }

    fun ImageView.loadImageWithGlide(url: String) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
    }
}
