package com.example.bincard.search.ui

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bincard.R
import com.example.bincard.databinding.FragmentSearchBinding
import com.example.bincard.info.ui.InfoFragment

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var inputText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = binding.editBinNum
        val keyboard = binding.keyboard
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        editText.setTextIsSelectable(true)
        val ic = editText.onCreateInputConnection(EditorInfo())
        keyboard.setInputConnection(ic)
        editText.isFocusable = false
        editText.addTextChangedListener(
            beforeTextChanged = { s, start, count, after -> },
            onTextChanged = { s, start, before, count -> },
            afterTextChanged = { s ->
                inputText = s.toString()
            })

        binding.keyboard.mButtonEnter?.setOnClickListener {
            if (inputText.length in 6..8) {
                findNavController().navigate(
                    R.id.action_navigation_search_to_infoFragment,
                    InfoFragment.createArgs(inputText)
                )
            } else {
                val color = ContextCompat.getColor(requireActivity(), R.color.red)
                binding.textViewFirstNumConst.setTextColor(color)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}