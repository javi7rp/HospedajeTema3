package com.example.hospedajetema3.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.models.Juego
import com.example.hospedajetema3.databinding.RecyclerViewBinding
import com.bumptech.glide.Glide
class ViewJuego (view: View,
                 var deleteOnClick: (Int) -> Unit,
                 var updateOnClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder (view){
    private var binding: RecyclerViewBinding
    init {
        binding = RecyclerViewBinding.bind(view)
    }
    fun renderize(juego: Juego, context: Context){
        binding.txtviewName.setText(juego.name)
        binding.txtviewPlataforma.setText(juego.plataforma)
        binding.txtviewAnno.setText(juego.anno)
        binding.txtviewNota.setText(juego.nota)
        val resourceId = context.resources.getIdentifier(
            juego.image, "drawable", context.packageName
        )
        binding.ivJuego.setImageResource(resourceId)

    }

    /*
    loadImageWithGlide(binding.ivJuego, juego.image)
    fun loadImageWithGlide(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
*/
   fun setOnClickListener(position: Int){
        binding.btnEdit.setOnClickListener {
            Log.i("aaaa" , "edit juego")
            updateOnClick(position)
        }
        binding.btnDelete.setOnClickListener {
            Log.i("aaaa" , "delete juego")
            deleteOnClick(position)
        }

    }
}
