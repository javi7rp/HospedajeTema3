import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hospedajetema3.R

class RewardFragment : Fragment() {

    private lateinit var imageButton: ImageButton
    private lateinit var handler: Handler
    private var isOnState = false // Comienza en off

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reward, container, false)

        imageButton = view.findViewById(R.id.reclamarReward)
        handler = Handler()

        updateImageButton()

        handler.postDelayed(imageChanger, 10000)

        imageButton.setOnClickListener {
            if (isOnState) {
                // Cambia el estado de la recompensa de on a off
                isOnState = false
                updateImageButton()
                Toast.makeText(context, "RECOMPENSA RECLAMADA", Toast.LENGTH_SHORT).show()

            } else {
                handler.removeCallbacks(imageChanger)
                handler.postDelayed(imageChanger, 10000)
            }
        }

        return view
    }

    // Runnable para cambiar la imagen después de cierto tiempo
    private val imageChanger = object : Runnable {
        override fun run() {
            isOnState = true
            updateImageButton()

            handler.postDelayed(this, 10000) // Cambia la imagen cada 2 segundos
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
