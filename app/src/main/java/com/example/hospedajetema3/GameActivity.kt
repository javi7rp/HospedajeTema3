package com.example.hospedajetema3


import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class GameActivity : AppCompatActivity(){
    private lateinit var questionTextView: TextView
    private lateinit var answerOptionsGroup: RadioGroup
    private lateinit var submitAnswerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        questionTextView = findViewById(R.id.questionTextView)
        answerOptionsGroup = findViewById(R.id.answerOptionsGroup)
        submitAnswerButton = findViewById(R.id.submitAnswerButton)

        // Inicializa el juego con la primera pregunta
        showQuestion("¿Cuál NO es una carta del Clash Royale?", listOf("Bolero", "Montapuercos", "Bruja Arquera", "Principe Oscuro"))
    }

    private fun showQuestion(question: String, options: List<String>) {
        // Muestra la pregunta
        questionTextView.text = question

        // Agrega opciones de respuesta dinámicamente
        answerOptionsGroup.removeAllViews()
        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            answerOptionsGroup.addView(radioButton)
        }

        // Configura el botón de respuesta
        submitAnswerButton.setOnClickListener {
            val selectedOptionId = answerOptionsGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val selectedAnswer = selectedRadioButton.text.toString()

                // Lógica para verificar la respuesta y mostrar la siguiente pregunta
                handleAnswer(selectedAnswer)
            }
        }
    }
    private fun handleAnswer(selectedAnswer: String) {
        val correctAnswer = "Bruja Arquera"

        if (selectedAnswer == correctAnswer) {
            showToast("CORRECTO!!")
        } else {
            showToast("ERROR !! Era la bruja Arquera...")
        }

        // Muestra la siguiente pregunta (puedes implementar esta lógica según tu estructura de juego)
        // showNextQuestion()

        finish()
    }

    private fun showToast(message: String) {
        // Función auxiliar para mostrar mensajes Toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}