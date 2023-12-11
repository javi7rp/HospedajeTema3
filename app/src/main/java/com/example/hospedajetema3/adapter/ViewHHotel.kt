package com.example.hospedajetema3.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.models.Hotel
import com.example.hospedajetema3.databinding.RecyclerViewBinding
import com.bumptech.glide.Glide
class ViewHHotel (view: View,
                    var deleteOnClick: (Int) -> Unit,
                    var updateOnClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder (view){
    private var binding: RecyclerViewBinding
    init {
        binding = RecyclerViewBinding.bind(view)
    }
    fun renderize(hotel : Hotel){
        binding.txtviewName.setText(hotel.name)
        binding.txtviewCity.setText(hotel.city)
        binding.txtviewProvince.setText(hotel.province)
        binding.txtviewPhone.setText(hotel.phone)
        loadImageWithGlide(binding.ivHotel, hotel.image)
    }

    fun loadImageWithGlide(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    private fun setOnClickListener(position: Int){
        binding.btnEdit.setOnClickListener {
            updateOnClick(position)
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }

    }
}
