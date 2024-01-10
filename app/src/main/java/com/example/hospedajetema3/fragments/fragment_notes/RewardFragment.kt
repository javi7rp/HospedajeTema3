import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hospedajetema3.R
import kotlin.random.Random

class RewardFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var imageButton: ImageButton
    private lateinit var handler: Handler
    private var isOnState = false // Comienza en off
    private var n = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reward, container, false)

        imageButton = view.findViewById(R.id.reclamarReward)
        handler = Handler()

        updateImageButton()

        handler.postDelayed(imageChanger, 2000)

        imageButton.setOnClickListener {
            if (isOnState) {
                // Cambia el estado de la recompensa de on a off
                isOnState = false
                updateImageButton()
                Toast.makeText(context, "RECOMPENSA RECLAMADA", Toast.LENGTH_SHORT).show()
                n = Random.nextInt(6)
                when (n){
                    1 -> {
                        imageView = view.findViewById(R.id.reward_1)
                        imageView.visibility = View.VISIBLE
                    }
                    2 -> {
                        imageView = view.findViewById(R.id.reward_2)
                        imageView.visibility = View.VISIBLE
                    }
                    3 -> {
                        imageView = view.findViewById(R.id.reward_3)
                        imageView.visibility = View.VISIBLE
                    }
                    4 -> {
                        imageView = view.findViewById(R.id.reward_4)
                        imageView.visibility = View.VISIBLE
                    }
                    5 -> {
                        imageView = view.findViewById(R.id.reward_5)
                        imageView.visibility = View.VISIBLE
                    }
                }



            } else {
                handler.removeCallbacks(imageChanger)
                handler.postDelayed(imageChanger, 2000)
            }
        }

        return view
    }



    // Runnable para cambiar la imagen después de cierto tiempo
    private val imageChanger = object : Runnable {
        override fun run() {
            isOnState = true
            updateImageButton()

            handler.postDelayed(this, 2000) // Cambia la imagen cada 2 segundos
        }
    }

    // Método para actualizar la imagen del ImageButton según el estado
    private fun updateImageButton() {
        if (isOnState) {
            imageButton.setImageResource(R.drawable.recompensa_on)
        } else {
            imageButton.setImageResource(R.drawable.recompensa_off)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Asegúrate de detener el temporizador al destruir la vista para prevenir memory leaks
        handler.removeCallbacks(imageChanger)
    }
}
