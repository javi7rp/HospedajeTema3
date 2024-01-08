package com.example.hospedajetema3



import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.hospedajetema3.fragments.FragmentFav
import com.example.hospedajetema3.fragments.FragmentInicio
import com.example.hospedajetema3.fragments.FragmentNotas
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
            var fragmentTag: String? = null
            when (item.itemId) {
                R.id.nav_inicio -> {
                    selectedFragment = FragmentInicio()
                    fragmentTag = "FragmentInicioTag"
                }
                R.id.nav_notas -> {
                    selectedFragment = FragmentNotas()
                    fragmentTag = "FragmentNotasTag"
                }
                R.id.nav_fav -> {
                    selectedFragment = FragmentFav()
                    fragmentTag = "FragmentFavTag"
                }
                R.id.nav_perfil -> {
                    selectedFragment = FragmentPerfil()
                    fragmentTag = "FragmentPerfilTag"
                }
            }
            val existingFragment = supportFragmentManager.findFragmentByTag(fragmentTag)

            if (existingFragment == null) {
                // El fragmento no existe, así que crea una nueva transacción y agrégalo
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment!!, fragmentTag)
                    .commit()
            } else {
                // El fragmento ya existe, así que simplemente muéstralo
                supportFragmentManager.beginTransaction()
                    .show(existingFragment)
                    .commit()
            }
            return@OnNavigationItemSelectedListener true

            true
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //showToast("boton seleccionado")
            R.id.action_settings -> openSystemSettings()
            R.id.action_about -> showAboutDialog()
            R.id.action_exit -> exitApp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openSystemSettings() {
        val intent = Intent(Settings.ACTION_SETTINGS)
        startActivity(intent)
    }
    private fun showAboutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Acerca de la app")
        builder.setMessage("VIDEOJUEGOS JAVI\n" +
                "Desarrollado por: Javi Redondo\n\n" +
                "Versión actual: 1.3\n\n" +
                "Para mas informacion, visite: JaviAppProyectoFinal.com\n")
        builder.setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
    private fun exitApp() {
        System.exit(0)
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
