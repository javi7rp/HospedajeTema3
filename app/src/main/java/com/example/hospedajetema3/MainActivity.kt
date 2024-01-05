package com.example.hospedajetema3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hospedajetema3.fragments.FragmentInicio
import com.example.hospedajetema3.fragments.FragmentLupa
import com.example.hospedajetema3.fragments.FragmentFav
import com.example.hospedajetema3.fragments.FragmentPerfil
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    //mi metodo con el problema de se resetea el fragmento cada vez que navegas en el menu
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,FragmentInicio())
            .commit()
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_inicio -> selectedFragment = FragmentInicio()
                R.id.nav_lupa -> selectedFragment = FragmentLupa()
                R.id.nav_fav -> selectedFragment = FragmentFav()
                R.id.nav_perfil -> selectedFragment = FragmentPerfil()
            }
            supportFragmentManager.beginTransaction()

                .replace(R.id.fragment_container,selectedFragment!!)
                .commit()

            true
        }


    /*
    //metodo con hide y show()
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentInicio: FragmentInicio
    private lateinit var fragmentLupa: FragmentLupa
    private lateinit var fragmentFav: FragmentFav
    private lateinit var fragmentPerfil: FragmentPerfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        // Inicializa los fragmentos
        fragmentInicio = FragmentInicio()
        fragmentLupa = FragmentLupa()
        fragmentFav = FragmentFav()
        fragmentPerfil = FragmentPerfil()

        if (savedInstanceState == null){
            // Añade el fragmento inicial
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentPerfil)
                .add(R.id.fragment_container, fragmentLupa)
                .add(R.id.fragment_container,fragmentInicio)
                .add(R.id.fragment_container, fragmentFav)
                .commit()

            // Oculta todos los fragmentos excepto el inicial
            supportFragmentManager.beginTransaction()
                .hide(fragmentLupa)
                .hide(fragmentFav)
                .hide(fragmentPerfil)
                .commit()
        }
        // Configura el listener del BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> showFragment(fragmentInicio)
                R.id.nav_lupa -> showFragment(fragmentLupa)
                R.id.nav_fav -> showFragment(fragmentFav)
                R.id.nav_perfil -> showFragment(fragmentPerfil)
            }
            true
        }

    private fun showFragment(fragment: Fragment) {
        // Oculta todos los fragmentos
        supportFragmentManager.beginTransaction()
            .hide(fragmentInicio)
            .hide(fragmentLupa)
            .hide(fragmentFav)
            .hide(fragmentPerfil)
            .commit()

        // Muestra el fragmento deseado
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    */

}
