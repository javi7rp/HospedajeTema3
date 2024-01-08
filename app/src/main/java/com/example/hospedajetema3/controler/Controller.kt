package com.example.hospedajetema3.controler

import android.app.AlertDialog
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.R.layout.simple_spinner_item
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.MainActivity
import com.example.hospedajetema3.adapter.AdapterJuego
import com.example.hospedajetema3.dao.DaoJuegos2
import com.example.hospedajetema3.databinding.FragmentInicioBinding
import com.example.hospedajetema3.fragments.FragmentInicio
import com.example.hospedajetema3.models.Juego
import java.util.UUID

class Controller ( val context : Context, val binding: FragmentInicioBinding){
    private lateinit var listJuego : MutableList<Juego> //lista de objetos
    private lateinit var adapterJuego: AdapterJuego
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: ImageButton

    private var favJuegos: MutableList<Juego> = mutableListOf() //Lista de juegos fav

    init {
        initData()
    }
    fun initData(){

        listJuego = DaoJuegos2.myDao.toMutableList()
    }

    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listJuego.forEach{
            println (it)
        }
    }
    fun setAdapter() { // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
        val myActivity = context as MainActivity
        adapterJuego = AdapterJuego(
            listJuego,
            {gameId -> deleteJuego(gameId)},
            {gameId -> updateJuego(gameId)},
            {gameId -> addFav(gameId) }
        )
        binding.myRecyclerView.adapter = adapterJuego
    }

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun setAddButton(addButton: ImageButton) {
        this.addButton = addButton
        this.addButton.setOnClickListener {
            addJuego()
        }
    }

    private fun addJuego() {
        mostrarCrearDialogo(object : DialogCallbackC{
            override fun onDialogResult(newJuego: Array<String>, isCanceled: Boolean) {
                if (!isCanceled){
                    val updatedJuego = Juego(generarId(),newJuego[0],newJuego[1],newJuego[2],newJuego[3], newJuego[4])
                    listJuego.add(updatedJuego)
                    //Toast.makeText(context, "JUEGO CREADO, POSICION: " + (listJuego.size),Toast.LENGTH_LONG).show()
                    //Toast.makeText(context, updatedJuego.toString(),Toast.LENGTH_LONG).show()

                    val newPos = (listJuego.size-1)
                    adapterJuego.notifyItemInserted(newPos)

                    recyclerView.smoothScrollToPosition(newPos)
                }
            }
        })
    }



    fun deleteJuego(gameId: String){
        Log.i("aaaa" , "delete juego")

        val selectedJuego = listJuego.find { it.id == gameId }
        val pos = listJuego.indexOf(selectedJuego)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Eliminar Juego")
        builder.setMessage("Deseas eliminar el juego: " + listJuego[pos].name)

        builder.setPositiveButton("Sí") { dialog, which ->
            Toast.makeText(context, "Borrado el " + listJuego[pos].name,Toast.LENGTH_LONG).show()
            listJuego.removeAt(pos)
            //notificar el cambio
            adapterJuego.notifyItemRemoved(pos)
            adapterJuego.notifyItemRangeChanged(pos, listJuego.size)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }


    fun updateJuego(gameId: String) {
        Log.i("aaaa" , "edit juego")
        val selectedJuego = listJuego.find { it.id == gameId }
        if (selectedJuego != null) {
            mostrarUpdateDialogo(selectedJuego, object : DialogCallbackC {
                override fun onDialogResult(newJuego: Array<String>, isCanceled: Boolean) {
                    if (!isCanceled) {
                        val imagen = selectedJuego.image
                        val pos = listJuego.indexOf(selectedJuego)
                        listJuego.removeAt(pos)
                        val updatedJuego = Juego(
                            generarId(),
                            newJuego[0],
                            newJuego[1],
                            newJuego[2],
                            newJuego[3],
                            imagen,
                            false
                        )
                        listJuego.add(pos, updatedJuego)

                        adapterJuego.notifyItemChanged(pos)
                    }
                }
            })
        }
    }
    private fun mostrarUpdateDialogo(selectedJuego: Juego, callBack: DialogCallbackC){
        val newJuego = Array(4){""}

        var isCanceled = false
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Actualizar Juego ")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val textName = TextView(context)
        textName.text = "Nombre: "
        layout.addView(textName)
        val name = EditText(context)
        name.setText(selectedJuego.name)
        layout.addView(name)

        val textPlat = TextView(context)
        textPlat.text = "Plataforma: "
        layout.addView(textPlat)
        val plat = EditText(context)
        plat.setText(selectedJuego.plataforma)
        layout.addView(plat)

        val textAnno = TextView(context)
        textAnno.text = "Anno: "
        layout.addView(textAnno)
        val anno = EditText(context)
        anno.setText(selectedJuego.anno)
        layout.addView(anno)

        val textNota = TextView(context)
        textNota.text = "Nota: "
        layout.addView(textNota)
        val nota = EditText(context)
        nota.setText(selectedJuego.nota)
        layout.addView(nota)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){dialog, which ->
            newJuego[0] = name.text.toString()
            newJuego[1] = plat.text.toString()
            newJuego[2] = anno.text.toString()
            newJuego[3] = nota.text.toString()

            if(newJuego[0] != "" && newJuego[1] != "" && newJuego[2] != "" && newJuego[3] != "" ){
                callBack.onDialogResult(newJuego, isCanceled)
                Toast.makeText(context, "JUEGO MODIFICADO", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "ERROR AL MODIFICAR, DEBES RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){
            dialog, which ->
            isCanceled = true
            callBack.onDialogResult(newJuego, isCanceled)
            dialog.cancel()
        }
        builder.show()
    }

    private fun mostrarCrearDialogo(callBack: DialogCallbackC) {
        val newJuego = Array(5) { "" }
        var isCanceled = false
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Añadir Juego ")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val textName = TextView(context)
        textName.text = "Nombre: "
        layout.addView(textName)
        val name = EditText(context)
        layout.addView(name)

        val textPlat = TextView(context)
        textPlat.text = "Plataforma: "
        layout.addView(textPlat)
        val plat = EditText(context)
        layout.addView(plat)

        val textAnno = TextView(context)
        textAnno.text = "Anno: "
        layout.addView(textAnno)
        val anno = EditText(context)
        layout.addView(anno)

        val textNota = TextView(context)
        textNota.text = "Nota: "
        layout.addView(textNota)
        val nota = EditText(context)
        layout.addView(nota)

        val textImagen = TextView(context)
        textImagen.text = "Selecciona una Imagen: "
        layout.addView(textImagen)

        val spinnerOption = arrayOf(
            "accion", "rpg", "multijugador", "miedo", "deportes",
            "estrategia", "aventura", "mundo_abierto", "misterio", "desconocido"
        )
        val spinner = Spinner(context)
        val adapter = ArrayAdapter(context, simple_spinner_item, spinnerOption)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        layout.addView(spinner)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar") { dialog, which ->

            newJuego[0] = name.text.toString()
            newJuego[1] = plat.text.toString()
            newJuego[2] = anno.text.toString()
            newJuego[3] = nota.text.toString()
            newJuego[4] = spinner.selectedItem.toString()

            if (newJuego[0] != "" && newJuego[1] != "" && newJuego[2] != "" && newJuego[3] != "" && newJuego[4] != "") {
                callBack.onDialogResult(newJuego, isCanceled)
            } else {
                Toast.makeText(
                    context,
                    "ERROR AL CREAR, DEBES RELLENAR TODOS LOS CAMPOS",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            isCanceled = true
            callBack.onDialogResult(newJuego, isCanceled)
            dialog.cancel()
        }

        builder.show()
    }

    private fun addFav(gameId: String){

        val selectedJuego = listJuego.find { it.id  == gameId }
        val pos = listJuego.indexOf(selectedJuego)

        val juego = listJuego[pos]
        juego.isFav = !juego.isFav //Cambiamos el rol
        if (juego.isFav){
            favJuegos.add(juego) //lo añadimos a la lista
        }else{
            val favPos = favJuegos.indexOfFirst { it == juego }
            if (favPos != -1){
                favJuegos.remove(juego) //lo eliminamos de la lista
            }
        }
        adapterJuego.notifyItemChanged(pos)

    }
    fun obtenerJuegosFav(): List<Juego> {
        return listJuego.filter { it.isFav }
    }



    fun toggleFav(gameId: String){

        val selectedJuego = listJuego.find { it.id  == gameId }
        val pos = listJuego.indexOf(selectedJuego)

        if (pos >= 0 && pos < listJuego.size) {
            // Cambia el estado de favorito
            listJuego[pos].isFav = !listJuego[pos].isFav

            // Notifica al adaptador sobre el cambio
            adapterJuego.notifyItemChanged(pos)
        }
    }

    // Método para obtener la lista de juegos
    fun getListJuego(): MutableList<Juego> {
        return listJuego
    }

    // Método para obtener el adaptador del RecyclerView
    fun getAdapterJuego(): AdapterJuego {
        return adapterJuego
    }

    private fun generarId(): String {
        return UUID.randomUUID().toString()
    }

    interface DialogCallbackC {
        fun onDialogResult(newJuego: Array<String>, isCanceled: Boolean)
    }
}