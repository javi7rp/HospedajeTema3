import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hospedajetema3.R
import kotlin.random.Random

class RewardFragment : Fragment() {

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
                n = Random.nextInt(20)+1
                Toast.makeText(context, "RECOMPENSA RECLAMADA POS: $n", Toast.LENGTH_SHORT).show()
                //showCartaDialog()
                val imagenId = "reward_$n"
                val resourceImageId = resources.getIdentifier(imagenId, "id", requireContext().packageName)
                val imageView = view.findViewById<ImageView>(resourceImageId)
                imageView.visibility = View.VISIBLE

                val textId = "rewardCont_$n"
                val resourceTextId = resources.getIdentifier(textId, "id", requireContext().packageName)
                val textView = view.findViewById<TextView>(resourceTextId)
                textView.visibility = View.VISIBLE

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
