package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onBackPressed()
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.forward1.setOnClickListener {
            if (binding.tag1.text.isNotEmpty()) {
                val action = FilterFragmentDirections.actionFilterFragmentToNotesFragment(
                    binding.tag1.text.toString(),
                    ""
                )
                findNavController().navigate(action)
            } else showToast("Тег 1 пустой!")
        }
        binding.forward2.setOnClickListener {
            if (binding.tag2.text.isNotEmpty()) {
                val action = FilterFragmentDirections.actionFilterFragmentToNotesFragment(
                    "",
                    binding.tag2.text.toString()
                )
                findNavController().navigate(action)
            } else showToast("Тег 2 пустой!")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FilterFragmentDirections.actionFilterFragmentToNotesFragment()
                    findNavController().navigate(action)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}