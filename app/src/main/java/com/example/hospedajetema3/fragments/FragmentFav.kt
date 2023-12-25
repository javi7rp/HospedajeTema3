package com.example.hospedajetema3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.R
import com.example.hospedajetema3.adapter.AdapterJuego
import com.example.hospedajetema3.controler.Controller
import com.example.hospedajetema3.databinding.FragmentInicioBinding
import com.example.hospedajetema3.models.Juego

class FragmentFav : Fragment() {
    private lateinit var controller: Controller
    private lateinit var binding: FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        // Inicializa el Controller
        controller = Controller(requireContext(), binding)
        controller.setAdapter()

        // Usa el binding para obtener referencias a vistas
        val myRecyclerView = binding.myRecyclerView
        controller.setRecyclerView(myRecyclerView)

        //val addButton = binding.btnAdd
        //controller.setAddButton(addButton)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        // Utiliza el binding para obtener referencias a vistas
        val myRecyclerView = binding.myRecyclerView
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val juegosFav = controller.obtenerJuegosFav().toMutableList()


        val adapterFav = AdapterJuego(
            juegosFav,
            {gameId -> controller.deleteJuego(gameId)},
            {gameId -> controller.updateJuego(gameId)},
            {gameId -> controller.toggleFav(gameId)},
        )

        myRecyclerView.adapter = adapterFav
    }

}