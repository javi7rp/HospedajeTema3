package com.example.hospedajetema3


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hospedajetema3.fragments.FragmentPerfil
import com.example.hospedajetema3.models.Pregunta
import com.example.hospedajetema3.objects_models.Variables
import java.util.Random

class GameActivity : AppCompatActivity(){
    private lateinit var questionTextView: TextView
    private lateinit var contQuestionTextView: TextView
    private lateinit var contAciertosTextView: TextView
    private lateinit var answerOptionsGroup: RadioGroup
    private lateinit var submitAnswerButton: Button

    private var questionCounter = 0
    private var contAciertos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        questionTextView = findViewById(R.id.questionTextView)
        contQuestionTextView = findViewById(R.id.contQuestionTextView)
        contAciertosTextView = findViewById(R.id.contAciertosTextView)
        answerOptionsGroup = findViewById(R.id.answerOptionsGroup)
        submitAnswerButton = findViewById(R.id.submitAnswerButton)


        showNextQuestion()

    }
    private val listaDePreguntas = listOf(
        Pregunta(
            "¿Cuál NO es una carta del Clash Royale?",
            listOf("Bolero", "Montapuercos", "Bruja Arquera", "Príncipe Oscuro"),
            "Bruja Arquera"
        ),
        Pregunta(
            "¿En qué año se llevó a cabo la Revolución Rusa?",
            listOf("1917", "1923", "1905", "1945"),
            "1917"
        ),
        Pregunta(
            "¿Quién escribió 'Romeo y Julieta'?",
            listOf("Charles Dickens", "William Shakespeare", "Jane Austen", "Fyodor Dostoevsky"),
            "William Shakespeare"
        ),
        Pregunta(
            "¿Cuál es el océano más grande del mundo?",
            listOf("Océano Atlántico", "Océano Índico", "Océano Pacífico", "Océano Ártico"),
            "Océano Pacífico"
        ),
        Pregunta(
            "¿En qué año se fundó la Organización de las Naciones Unidas (ONU)?",
            listOf("1920", "1945", "1960", "1980"),
            "1945"
        ),
        Pregunta(
            "¿Cuál es el elemento más abundante en la Tierra?",
            listOf("Hierro", "Oxígeno", "Silicio", "Aluminio"),
            "Oxígeno"
        ),
        Pregunta(
            "¿Quién pintó la Mona Lisa?",
            listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"),
            "Leonardo da Vinci"
        ),
        Pregunta(
            "¿En qué país se encuentra la Gran Muralla China?",
            listOf("India", "Japón", "China", "Corea del Sur"),
            "China"
        ),
        Pregunta(
            "¿Cuál es el río más largo del mundo?",
            listOf("Amazonas", "Nilo", "Yangtsé", "Misisipi"),
            "Amazonas"
        ),
        Pregunta(
            "¿Quién fue el primer presidente de Estados Unidos?",
            listOf("Thomas Jefferson", "Abraham Lincoln", "George Washington", "John Adams"),
            "George Washington"
        ),
        Pregunta(
            "¿Cuál es el instrumento musical más antiguo del mundo?",
            listOf("Flauta", "Tambor", "Lira", "Arpa"),
            "Flauta"
        ),
        Pregunta(
            "¿Qué planeta es conocido como el 'Planeta Rojo'?",
            listOf("Venus", "Júpiter", "Marte", "Saturno"),
            "Marte"
        ),
        Pregunta(
            "¿Cuál es la capital de Francia?",
            listOf("Berlín", "Madrid", "París", "Roma"),
            "París"
        ),
        Pregunta(
            "¿En qué año se firmó la Declaración de Independencia de Estados Unidos?",
            listOf("1776", "1789", "1804", "1812"),
            "1776"
        ),
        Pregunta(
            "¿Cuál es la montaña más alta del mundo?",
            listOf("Mont Blanc", "Kilimanjaro", "Everest", "Aconcagua"),
            "Everest"
        ),
        Pregunta(
            "¿Quién es conocido como 'El Rey del Pop'?",
            listOf("Elvis Presley", "Michael Jackson", "Madonna", "The Beatles"),
            "Michael Jackson"
        ),
        Pregunta(
            "¿En qué año comenzó la Segunda Guerra Mundial?",
            listOf("1914", "1939", "1945", "1950"),
            "1939"
        ),
        Pregunta(
            "¿Cuál es el componente principal del aire?",
            listOf("Nitrógeno", "Oxígeno", "Dióxido de carbono", "Argón"),
            "Nitrógeno"
        ),
        Pregunta(
            "¿Quién escribió 'Don Quijote de la Mancha'?",
            listOf("Miguel de Cervantes", "Gabriel García Márquez", "William Faulkner", "Jane Austen"),
            "Miguel de Cervantes"
        ),
        Pregunta(
            "¿Cuál es el metal más abundante en la corteza terrestre?",
            listOf("Hierro", "Oro", "Plata", "Aluminio"),
            "Hierro"
        ),
        Pregunta(
            "¿Qué país es conocido como la 'Tierra del Sol Naciente'?",
            listOf("China", "India", "Japón", "Corea del Sur"),
            "Japón"
        ),
        Pregunta(
            "¿Cuál es la capital de Rusia?",
            listOf("Moscú", "San Petersburgo", "Kiev", "Minsk"),
            "Moscú"
        ),
        Pregunta(
            "¿En qué año se produjo la Revolución Industrial?",
            listOf("1600", "1750", "1850", "1900"),
            "1850"
        ),
        Pregunta(
            "¿Quién fue el primer ser humano en viajar al espacio?",
            listOf("Yuri Gagarin", "Neil Armstrong", "Buzz Aldrin", "Valentina Tereshkova"),
            "Yuri Gagarin"
        ),
        Pregunta(
            "¿Cuál es el país más grande del mundo en términos de superficie terrestre?",
            listOf("Estados Unidos", "China", "Canadá", "Rusia"),
            "Rusia"
        ),
        Pregunta(
            "¿Qué elemento químico tiene el símbolo 'Fe'?",
            listOf("Hierro", "Oro", "Plata", "Mercurio"),
            "Hierro"
        ),
        Pregunta(
            "¿Cuál es el continente más grande del mundo?",
            listOf("África", "Asia", "América del Norte", "Europa"),
            "Asia"
        ),
        Pregunta(
            "¿Quién fue el primer presidente de México?",
            listOf("Miguel Hidalgo", "Benito Juárez", "Porfirio Díaz", "Agustín de Iturbide"),
            "Agustín de Iturbide"
        ),
        Pregunta(
            "¿En qué año se llevó a cabo la Revolución Mexicana?",
            listOf("1810", "1821", "1910", "1920"),
            "1910"
        ),
        Pregunta(
            "¿Cuál es el desierto más grande del mundo?",
            listOf("Atacama", "Gobi", "Sáhara", "Kalahari"),
            "Sáhara"
        ),
        Pregunta(
            "¿Quién fue el primer emperador romano?",
            listOf("Julio César", "Marco Aurelio", "Augusto", "Nerón"),
            "Augusto"
        )

    )

    private fun obtener10PreguntasAzar():List<Pregunta>{
        return listaDePreguntas.shuffled(Random()).take(10)
    }

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
                handleAnswer(selectedAnswer, correctAnswer)
            }
        }
    }
    private fun handleAnswer(selectedAnswer: String, correctAnswer: String) {
        questionCounter++
        if (selectedAnswer == correctAnswer) {
            contAciertos ++
            showAnswerDialog(true, correctAnswer)
            contAciertosTextView.setText("Aciertos: " + contAciertos + "/" + questionCounter)
        } else {
            showAnswerDialog(false, correctAnswer)
            contAciertosTextView.setText("Aciertos: " + (contAciertos) + "/" + questionCounter)

        }
    }


    private fun showNextQuestion() {
        val preguntasAzar = obtener10PreguntasAzar()
        val nextQuestion = preguntasAzar[questionCounter]
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
        }else{
            builder.setMessage("RESPUESTA INCORRECTA\nLa solución era: " + correctAnswer)
        }

        builder.setPositiveButton("ACEPTAR") { dialog, _ -> dialog.dismiss()
            //Muestra la siguiente pregunta (puedes implementar esta lógica según tu estructura de juego)
            if (questionCounter < 10){
                showNextQuestion()
            }else{
                showFinalDialog(contAciertos)

            }

        }
        builder.show()
    }
    private fun showFinalDialog(contAciertos: Int) {

        if (contAciertos == 10){
            Variables.allPreguntas ++
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("FIN DEL JUEGO")
        builder.setMessage("HAS ACERTADO: " + contAciertos + "/" + questionCounter)
        builder.setPositiveButton("TERMINAR") { dialog, _ ->
            dialog.dismiss()
            finish()
            val fragmentPerfil = supportFragmentManager.findFragmentById(R.id.fragment_container) as? FragmentPerfil
            fragmentPerfil?.let {
                if (!it.isResultadoActualizado) {
                    it.actualizarResultado(contAciertos)
                    Log.d("IIIIIII", "Encuentra el fragmento")
                }else {
                    Log.d("IIIIIII", "Fragmento encontrado, pero el resultado ya está actualizado")
                }
            } ?: Log.d("IIIIIII", "Fragmento no encontrado")

        }
        builder.show()
    }


}