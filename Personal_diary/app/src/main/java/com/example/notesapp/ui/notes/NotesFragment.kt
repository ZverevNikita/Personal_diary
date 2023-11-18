package com.example.notesapp.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.ui.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.navArgs
import androidx.activity.OnBackPressedCallback

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val args: NotesFragmentArgs by navArgs()

    private val viewModel by viewModels<NotesViewModel>()

    private val notesAdapter = NotesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onBackPressed()
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
        if (args.tag1.isNotEmpty()) {
            viewModel.getByTag1(args.tag1)
        } else if (args.tag2.isNotEmpty()) {
            viewModel.getByTag2(args.tag2)
        } else {
            viewModel.getNotes()
        }
    }

    private fun initViews() {
        with(binding) {
            notesRecycler.apply {
                adapter = notesAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            notesAdapter.itemClick = { note ->
                val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(
                    note.id,
                    note.title,
                    note.message,
                    note.tag1,
                    note.tag2
                )
                navigate(action)
            }

            notesAdapter.itemDelete = { id ->
                viewModel.deleteNote(id)
                viewModel.getNotes()
            }

            addButton.setOnClickListener {
                val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
                navigate(action)
            }

            updateButton.setOnClickListener {
                viewModel.getNotes()
            }

            container.setOnClickListener {
                navigate(NotesFragmentDirections.actionNotesFragmentToFilterFragment())
            }
        }
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun initObservers() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            notesAdapter.setNotes(it)
        }
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}