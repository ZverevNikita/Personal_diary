package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentAddNoteBinding
import com.example.notesapp.domain.entities.NoteModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onBackPressed()
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addButton.setOnClickListener {
            if (addNote()) {
                val action = AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun addNote(): Boolean {
        val title = binding.titleInput.text.toString()
        if (title.isEmpty()) {
            showToast("Заголовок пустой")
        } else {
            val message = binding.messageInput.text.toString()
            if (message.isEmpty()) {
                showToast("Текст заметки пустой!")
            } else {
                val tag1 = binding.tag1Input.text.toString()
                if (tag1.isEmpty()) {
                    showToast("Тег 1 пустой!")
                } else {
                    val tag2 = binding.tag2Input.text.toString()
                    if (tag2.isEmpty()) {
                        showToast("Тег 2 пустой!")
                    } else {
                        val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault())
                        val currentDateAndTime = sdf.format(Date())
                        viewModel.addNote(
                            NoteModel(
                                0,
                                title,
                                message,
                                currentDateAndTime,
                                tag1,
                                tag2
                            )
                        )
                    }
                }
                return true
            }
        }
        return false
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment()
                    findNavController().navigate(action)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}