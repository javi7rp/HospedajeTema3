package com.example.hospedajetema3.fragments.fragment_notes

import AddNoteDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.hospedajetema3.R
class NotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        val btnAddNote: Button = view.findViewById(R.id.btnAddNote)
        btnAddNote.setOnClickListener {
            showAddNoteDialog()
        }

        return view
    }

    private fun showAddNoteDialog() {
        val addNoteDialog = AddNoteDialog(this, this)
        addNoteDialog.show(requireActivity().supportFragmentManager, "AddNoteDialog")
    }
    override fun onNoteAdded(note: String, selectedColor: String) {
        notesList.add(note)
        noteAdapter.notifyItemInserted(notesList.size - 1)

        // Puedes usar el color seleccionado para personalizar la apariencia, por ejemplo, estableciendo el fondo del diálogo.
        // dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor(selectedColor)))
    }

    override fun onColorSelected(color: String) {
        // Puedes realizar acciones adicionales cuando se selecciona un color.
    }

}