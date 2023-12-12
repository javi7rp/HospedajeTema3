package com.example.hospedajetema3.controler

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.material3.FabPosition
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.MainActivity
import com.example.hospedajetema3.adapter.AdapterHotel
import com.example.hospedajetema3.dao.DaoHotels2
import com.example.hospedajetema3.models.Hotel

class Controller ( val context : Context){
    private lateinit var listHotels : MutableList<Hotel> //lista de objetos
    private lateinit var adapterHotel: AdapterHotel
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: ImageButton

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
        adapterHotel = AdapterHotel(
            listHotels,
            {pos -> deleteHotel(pos)},
            {pos -> updateHotel(pos)}
        )
        myActivity.binding.myRecyclerView.adapter = adapterHotel
    }

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun setAddButton(addButton: ImageButton) {
        this.addButton = addButton
        this.addButton.setOnClickListener {
            addHotel(listHotels.size+1)
        }
    }

    private fun addHotel(pos: Int) {
        mostrarCrearDialogo(object : DialogCallback{
            override fun onDialogResult(newHotel: Array<String>, isCanceled: Boolean) {
                if (!isCanceled){
                    val updatedHotel = Hotel(newHotel[0],newHotel[1],newHotel[2],newHotel[3], newHotel[4])
                    listHotels.add(pos, updatedHotel)
                    Log.i("CREADO", "HOTEL CREADO " + updatedHotel.toString())

                    adapterHotel.notifyItemChanged(pos)
                }
            }
        })
    }



    fun deleteHotel(pos:Int){
        Toast.makeText(context, "Borramos el hotel " + (pos+1),Toast.LENGTH_LONG).show()
        listHotels.removeAt(pos)
        //notificar el cambio
        adapterHotel.notifyItemRemoved(pos)
        adapterHotel.notifyItemRangeChanged(pos, listHotels.size)
    }


    private fun updateHotel(pos: Int) {
        mostrarUpdateDialogo(object : DialogCallback{
            override fun onDialogResult(newHotel: Array<String>, isCanceled: Boolean) {
                if (!isCanceled){
                    val imagen =  listHotels[pos].image
                    listHotels.removeAt(pos)
                    val updatedHotel = Hotel(newHotel[0],newHotel[1],newHotel[2],newHotel[3], imagen)
                    listHotels.add(pos, updatedHotel)
                    Log.i("CREADO", "HOTEL MODIFICADO " + updatedHotel.toString())

                    adapterHotel.notifyItemChanged(pos)
                }
            }
        })
    }
    private fun mostrarUpdateDialogo(callBack: DialogCallback){
        val newHotel = Array(4){""}
        var isCanceled = false
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Actualizar Hotel ")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val textName = TextView(context)
        textName.text = "Nombre: "
        layout.addView(textName)
        val name = EditText(context)
        layout.addView(name)

        val textCity = TextView(context)
        textCity.text = "Ciudad: "
        layout.addView(textCity)
        val city = EditText(context)
        layout.addView(city)

        val textProvince = TextView(context)
        textProvince.text = "Provincia: "
        layout.addView(textProvince)
        val province = EditText(context)
        layout.addView(province)

        val textPhone = TextView(context)
        textPhone.text = "Telefono: "
        layout.addView(textPhone)
        val phone = EditText(context)
        layout.addView(phone)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){
            dialog, which ->
            newHotel[0] = name.text.toString()
            newHotel[1] = city.text.toString()
            newHotel[2] = province.text.toString()
            newHotel[3] = phone.text.toString()
            callBack.onDialogResult(newHotel, isCanceled)

        }

        builder.setNegativeButton("Cancelar"){
            dialog, which ->
            isCanceled = true
            callBack.onDialogResult(newHotel, isCanceled)
            dialog.cancel()
        }

        builder.show()
    }

    private fun mostrarCrearDialogo(callBack: DialogCallback){
        val newHotel = Array(5){""}
        var isCanceled = false
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Añadir Hotel ")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val textName = TextView(context)
        textName.text = "Nombre: "
        layout.addView(textName)
        val name = EditText(context)
        layout.addView(name)

        val textCity = TextView(context)
        textCity.text = "Ciudad: "
        layout.addView(textCity)
        val city = EditText(context)
        layout.addView(city)

        val textProvince = TextView(context)
        textProvince.text = "Provincia: "
        layout.addView(textProvince)
        val province = EditText(context)
        layout.addView(province)

        val textPhone = TextView(context)
        textPhone.text = "Telefono: "
        layout.addView(textPhone)
        val phone = EditText(context)
        layout.addView(phone)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){
            dialog, which ->
            newHotel[0] = name.text.toString()
            newHotel[1] = city.text.toString()
            newHotel[2] = province.text.toString()
            newHotel[3] = phone.text.toString()
            callBack.onDialogResult(newHotel, isCanceled)

        }

        builder.setNegativeButton("Cancelar"){
            dialog, which ->
            isCanceled = true
            callBack.onDialogResult(newHotel, isCanceled)
            dialog.cancel()
        }

        builder.show()
    }

    interface DialogCallback {
        fun onDialogResult(newHotel: Array<String>, isCanceled: Boolean)
    }
}