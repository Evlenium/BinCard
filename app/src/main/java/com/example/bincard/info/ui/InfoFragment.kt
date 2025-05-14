package com.example.bincard.info.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.bincard.R
import com.example.bincard.databinding.FragmentInfoBinding
import com.example.bincard.search.presentation.SearchViewModel
import com.example.bincard.search.presentation.model.CardInfoState
import com.example.bincard.search.presentation.model.CardInformationUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private var binNum: String? = null
    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binNum = it.getString(ARGS_BIN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binNum != null) {
            binding.textViewBin.text = binNum
            searchViewModel.searchBin(binNum!!)
        }
        searchViewModel.observeSearchState().observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun render(state: CardInfoState) {
        when (state) {
            is CardInfoState.Content -> showContent(state.data)
            is CardInfoState.Error -> showError(state.errorMessage)
            is CardInfoState.Loading -> showLoading()
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBar.isVisible = true
            placeholderViewGroup.isVisible = false
        }
    }

    private fun showError(message: String) {
        with(binding) {
            placeholderViewGroup.isVisible = true
            progressBar.isVisible = false
            placeholderTextView.isVisible = true
            placeholderTextView.text = message
            placeholderImageView.setImageResource(R.drawable.placeholder_error_search)
        }
    }

    private fun showContent(data: CardInformationUI) {
        with(binding) {
            searchViewModel.saveData(data)
            placeholderViewGroup.isVisible = true
            progressBar.isVisible = false
            placeholderTextView.isVisible = false
            placeholderImageView.setImageResource(R.drawable.placeholder_vacancy_search)
            textViewCountry.text = data.country
            textViewCoordinate.text = data.coordinate
            textViewCardType.text = data.cardType
            textViewBankURL.text = data.bankURL
            textViewBankPhone.text = data.bankPhone
            textViewBankSite.text = data.baskSite
            textViewBankCity.text = data.bankCity
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARGS_BIN = "bin"

        fun createArgs(bin: String): Bundle =
            bundleOf(
                ARGS_BIN to bin,
            )
    }
}