package com.example.hospedajetema3


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hospedajetema3.dialogues.DialogCallback
import com.example.hospedajetema3.models.Usuario
import com.example.hospedajetema3.objects_models.ListaUser

class LoginActivity : AppCompatActivity(), DialogCallback {

    private lateinit var userEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var iniciarSesionButton: Button
    private lateinit var registrarseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_inicio_sesion)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        userEditText = findViewById(R.id.user)
        passwordEditText = findViewById(R.id.password)
        iniciarSesionButton = findViewById(R.id.iniciarSesion)
        registrarseButton = findViewById(R.id.registrarse)
    }

    private fun setupListeners() {
        iniciarSesionButton.setOnClickListener {
            val user = userEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (ListaUser.verificarUsuario(Usuario(user,password))){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this, "USUARIO NO REGISTRADO o CAMPOS NO RELLENADOS", Toast.LENGTH_SHORT).show()
            }
        }
        registrarseButton.setOnClickListener {
            mostrarDialogoRegistro()
        }
    }

    private fun mostrarDialogoRegistro() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro De Usuario")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val textUser = TextView(this)
        textUser.text = "Usuario:"
        layout.addView(textUser)

        val inputUser = EditText(this)
        layout.addView(inputUser)

        val textPass = TextView(this)
        textPass.text = "Contraseña:"
        layout.addView(textPass)

        val inputPass = EditText(this)
        layout.addView(inputPass)

        val textPass2 = TextView(this)
        textPass2.text = "Confirmar Contraseña:"
        layout.addView(textPass2)

        val inputPass2 = EditText(this)
        layout.addView(inputPass2)

        builder.setView(layout)

        builder.setPositiveButton("Registrarse") { _, _ ->
            val user = inputUser.text.toString()
            val pass = inputPass.text.toString()
            val pass2 = inputPass2.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty() && pass2.isNotEmpty()) {
                if (pass.equals(pass2)){
                    onRegisterClicked(user,pass)
                }else{
                    Toast.makeText(this, "INTRODUCE LAS CONTRASEÑA CORRECTAMENTE", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    override fun onRegisterClicked(user: String, pass: String) {
       ListaUser.annadirUsuario(Usuario(user, pass))
        Toast.makeText(this, "REGISTADO CORRECTAMENTE User: $user - Password: $pass", Toast.LENGTH_SHORT).show()
    }




}