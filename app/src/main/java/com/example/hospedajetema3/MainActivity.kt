package com.example.hospedajetema3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospedajetema3.controler.Controller
import com.example.hospedajetema3.databinding.MainBinding
import com.example.hospedajetema3.fragments.FragmentInicio
import com.example.hospedajetema3.fragments.FragmentLupa
import com.example.hospedajetema3.fragments.FragmentNotas
import com.example.hospedajetema3.fragments.FragmentPerfil
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            FragmentInicio()
        ).commit()
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_inicio -> selectedFragment = FragmentInicio()
                R.id.nav_lupa -> selectedFragment = FragmentLupa()
                R.id.nav_notas -> selectedFragment = FragmentNotas()
                R.id.nav_perfil -> selectedFragment = FragmentPerfil()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!
            ).commit()
            true
        }
}
