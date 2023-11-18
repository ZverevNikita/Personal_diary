package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.databinding.FragmentEditNoteBinding
import com.example.notesapp.domain.entities.NoteModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import androidx.activity.OnBackPressedCallback

@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NotesViewModel>()

    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onBackPressed()
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.titleInput.setText(args.title)
        binding.messageInput.setText(args.message)
        binding.tag1Input.setText(args.tag1)
        binding.tag2Input.setText(args.tag2)
        binding.acceptButton.setOnClickListener {
            if (changeNote()) {
                val action = EditNoteFragmentDirections.actionEditNoteFragmentToNotesFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun changeNote(): Boolean {
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
                        viewModel.changeNote(
                            NoteModel(
                                args.id,
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
                    val action = EditNoteFragmentDirections.actionEditNoteFragmentToNotesFragment()
                    findNavController().navigate(action)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}