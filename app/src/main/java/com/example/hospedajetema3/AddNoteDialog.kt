import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.example.hospedajetema3.R

// AddNoteDialog.kt
class AddNoteDialog(
    private val onNoteAddedListener: OnNoteAddedListener,
    private val onColorSelectedListener: OnColorSelectedListener
) : DialogFragment() {

    interface OnNoteAddedListener {
        fun onNoteAdded(note: String, selectedColor: String)
    }

    interface OnColorSelectedListener {
        fun onColorSelected(color: String)
    }

    private lateinit var editTextNewNote: EditText
    private lateinit var buttonAddNote: Button
    private lateinit var spinnerColor: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_note, container, false)

        editTextNewNote = view.findViewById(R.id.editTextNewNote)
        buttonAddNote = view.findViewById(R.id.buttonAddNote)
        spinnerColor = view.findViewById(R.id.spinnerColor)

        // Configurar el adaptador para el spinner
        val colorAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.color_options,
            android.R.layout.simple_spinner_item
        )
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColor.adapter = colorAdapter

        buttonAddNote.setOnClickListener {
            addNote()
        }

        spinnerColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedColor = parent?.getItemAtPosition(position).toString()
                onColorSelectedListener.onColorSelected(selectedColor)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No es necesario implementar nada aquí
            }
        }

        return view
    }

    private fun addNote() {
        val newNote = editTextNewNote.text.toString().trim()
        if (newNote.isNotEmpty()) {
            val selectedColor = spinnerColor.selectedItem.toString()
            onNoteAddedListener.onNoteAdded(newNote, selectedColor)
            dismiss()
        }
    }
}
