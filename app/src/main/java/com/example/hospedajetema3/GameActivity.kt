package com.example.hospedajetema3


import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hospedajetema3.models.Pregunta

class GameActivity : AppCompatActivity(){
    private lateinit var questionTextView: TextView
    private lateinit var contQuestionTextView: TextView
    private lateinit var answerOptionsGroup: RadioGroup
    private lateinit var submitAnswerButton: Button

    private var questionCounter = 0
    private var contAciertos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        questionTextView = findViewById(R.id.questionTextView)
        contQuestionTextView = findViewById(R.id.contQuestionTextView)
        answerOptionsGroup = findViewById(R.id.answerOptionsGroup)
        submitAnswerButton = findViewById(R.id.submitAnswerButton)


        showFirstQuestion()

    }
    private val listaDePreguntas = listOf(
        Pregunta(
            "¿Cuál NO es una carta del Clash Royale?",
            listOf("Bolero", "Montapuercos", "Bruja Arquera", "Príncipe Oscuro"),
            "Bruja Arquera"
        ),
        Pregunta(
            "¿Cuál es la capital de España?",
            listOf("Londres", "Madrid", "París", "Berlín"),
            "Madrid"
        ),
    )

    private fun showQuestion(questionIndex: Int, question: String, options: List<String>, correctAnswer: String) {
        // Muestra la pregunta
        questionTextView.text = question
        contQuestionTextView.setText("Pregunta " + (questionIndex + 1))

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
                handleAnswer(questionIndex, selectedAnswer, correctAnswer)
            }
        }
    }
    private fun handleAnswer(questionIndex: Int, selectedAnswer: String, correctAnswer: String) {
        if (selectedAnswer == correctAnswer) {
            showAnswerDialog(true, correctAnswer)
        } else {
            showAnswerDialog(false, correctAnswer)
        }

        questionCounter ++
        //Muestra la siguiente pregunta (puedes implementar esta lógica según tu estructura de juego)
        if (questionCounter <= listaDePreguntas.size){
            showNextQuestion()
        }else{
            showFinalDialog(contAciertos)
            finish()
        }


    }
    private fun showFirstQuestion() {
        val nextQuestion = listaDePreguntas[questionCounter]
        showQuestion(
            questionCounter,
            nextQuestion.pregunta,
            nextQuestion.opciones,
            nextQuestion.respuestaCorrecta
        )
    }
    private fun showNextQuestion() {
        val nextQuestion = listaDePreguntas[questionCounter + 1]
        showQuestion(
            questionCounter,
            nextQuestion.pregunta,
            nextQuestion.opciones,
            nextQuestion.respuestaCorrecta
        )
    }
    private fun showAnswerDialog(acierto: Boolean, correctAnswer: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SOLUCIÓN")
        if (acierto){
            builder.setMessage("RESPUESTA CORRECTA!!")
            contAciertos++
        }else{
            builder.setMessage("RESPUESTA INCORRECTA\nLa solución era: " + correctAnswer)
        }

        builder.setPositiveButton("ACEPTAR") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
    private fun showFinalDialog(contAciertos: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("FIN DEL JUEGO")
        builder.setMessage("HAS ACERTADO: " + contAciertos)
        builder.setPositiveButton("TERMINAR") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

}