package com.example.hospedajetema3.fragments.fragment_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import com.example.hospedajetema3.R
class NotesFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var relativeLayout: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_notes, container, false)
        relativeLayout = rootView.findViewById(R.id.relativeLayoutContainer)

        val noteEditText: EditText = rootView.findViewById(R.id.noteEditText)
        val addNoteButton: Button = rootView.findViewById(R.id.addNoteButton)

        addNoteButton.setOnClickListener {
            val noteText = noteEditText.text.toString()

            if (noteText.isNotEmpty()) {
                addNoteToScreen(noteText)
                noteEditText.text.clear()
            }
        }

        return rootView
    }

    private fun addNoteToScreen(noteText: String) {
        val noteTextView = TextView(requireContext())
        noteTextView.text = noteText
        noteTextView.textSize = 16f

        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        if (relativeLayout.childCount > 0) {
            val lastNoteId = relativeLayout.getChildAt(relativeLayout.childCount - 2).id
            layoutParams.addRule(RelativeLayout.BELOW, lastNoteId)
        } else {
            layoutParams.addRule(RelativeLayout.BELOW, R.id.noteEditText)
        }

        layoutParams.topMargin = 16
        noteTextView.layoutParams = layoutParams

        // Asignar un ID único a cada nota
        noteTextView.id = View.generateViewId()

        relativeLayout.addView(noteTextView)
    }
}