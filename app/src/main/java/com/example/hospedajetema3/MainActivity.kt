package com.example.hospedajetema3



import FragmentNotas
import RewardFragment
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.hospedajetema3.fragments.FragmentFav
import com.example.hospedajetema3.fragments.FragmentInicio
import com.example.hospedajetema3.fragments.FragmentPerfil
import com.example.hospedajetema3.fragments.fragment_notes.InsigniasFragment
import com.example.hospedajetema3.fragments.fragment_notes.NotesFragment
import com.example.hospedajetema3.fragments.fragment_notes.OtherFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {


    /*
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

     */


/*
    //Menu de los 3 puntitos con las 3 opciones q he introducido
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
 */

/*
    //DrawerNav
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar la selección de elementos en el NavigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.fragmentInicio -> {
                    // Reemplazar el fragmento actual con el Fragmento1
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentInicio())
                        .commit()
                }
                R.id.fragmentNotas -> {
                    // Reemplazar el fragmento actual con el Fragmento2
                    val subMenu = navView.menu.findItem(R.id.fragmentNotas2)?.subMenu
                    subMenu?.let {
                        for (i in 0 until it.size()) {
                            val childItem = it.getItem(i)
                            childItem.isVisible = !childItem.isVisible
                        }
                    }
                }

                //Submenus del fragmentNotas
                R.id.fragment2_1 ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, OtherFragment())
                        .commit()
                }
                R.id.fragment2_2 ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, NotesFragment())
                        .commit()
                }
                R.id.fragment2_3 ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RewardFragment())
                        .commit()
                }
                R.id.fragment2_4 ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, InsigniasFragment())
                        .commit()
                }

                R.id.fragmentFav -> {
                    // Reemplazar el fragmento actual con el Fragmento3
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentFav())
                        .commit()
                }
                R.id.fragmentPerfil -> {
                    // Reemplazar el fragmento actual con el Fragmento4
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentPerfil())
                        .commit()
                }
            }

            // Cerrar el Drawer después de seleccionar un elemento
            if (menuItem.itemId != R.id.fragmentNotas) {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }
        // Configurar el icono de la ActionBar para abrir el Drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Mostrar el primer fragmento al iniciar la actividad
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FragmentInicio())
            .commit()


    }

    override fun onBackPressed() {
        // Cerrar el Drawer al presionar el botón de retroceso si está abierto
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Abrir o cerrar el Drawer según el estado actual
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START)
                } else {
                    // No cerrar el Drawer si se hace clic en el icono de hamburguesa
                    if (item.itemId != R.id.fragmentNotas) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

 */


    //metodo con hide y show()
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentInicio: FragmentInicio
    private lateinit var fragmentNotas: FragmentNotas
    private lateinit var fragmentFav: FragmentFav
    private lateinit var fragmentPerfil: FragmentPerfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        // Inicializa los fragmentos
        fragmentInicio = FragmentInicio()
        fragmentNotas = FragmentNotas()
        fragmentFav = FragmentFav()
        fragmentPerfil = FragmentPerfil()

        if (savedInstanceState == null){
            // Añade el fragmento inicial
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentPerfil)
                .add(R.id.fragment_container, fragmentNotas)
                .add(R.id.fragment_container,fragmentInicio)
                .add(R.id.fragment_container, fragmentFav)
                .commit()

            // Oculta todos los fragmentos excepto el inicial
            supportFragmentManager.beginTransaction()
                .hide(fragmentNotas)
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
                R.id.nav_notas -> showFragment(fragmentNotas)
                R.id.nav_fav -> showFragment(fragmentFav)
                R.id.nav_perfil -> showFragment(fragmentPerfil)
            }
            true
        }

    private fun showFragment(fragment: Fragment) {
        // Oculta todos los fragmentos
        supportFragmentManager.beginTransaction()
            .hide(fragmentInicio)
            .hide(fragmentNotas)
            .hide(fragmentFav)
            .hide(fragmentPerfil)
            .commit()

        // Muestra el fragmento deseado
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commit()
    }


}
