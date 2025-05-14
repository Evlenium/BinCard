package com.example.bincard.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bincard.R
import com.example.bincard.databinding.FragmentHistoryBinding
import com.example.bincard.history.presentation.HistoryViewModel
import com.example.bincard.history.presentation.model.CardInfoDBState
import com.example.bincard.search.presentation.model.CardInformationUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyViewModel by viewModel<HistoryViewModel>()

    private var cardInfoAdapter: CardInfoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.recyclerViewInfoList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyViewModel.fillDataCardInfo()
        historyViewModel.observeCardInfoState().observe(viewLifecycleOwner) { cardInfo ->
            renderState(cardInfo)
        }
    }

    private fun renderState(cardInfo: CardInfoDBState) {
        when (cardInfo) {
            is CardInfoDBState.Content -> showContent(cardInfo.cardInfo)
            is CardInfoDBState.Empty -> showPlaceholder()
        }
    }

    private fun showPlaceholder() {
        binding.recyclerViewInfoList.isVisible = false
        binding.placeholderViewGroup.isVisible = true
    }

    private fun showContent(cardInfo: List<CardInformationUI>) {
        cardInfoAdapter = CardInfoAdapter(cardInfo) {
            deleteInfo(it)
        }
        binding.recyclerViewInfoList.adapter = cardInfoAdapter
        binding.recyclerViewInfoList.isVisible = true
        binding.placeholderViewGroup.isVisible = false
        cardInfoAdapter?.submitList(cardInfo)
    }

    private fun deleteInfo(card: CardInformationUI) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.do_you_want_delete_card)
            .setNegativeButton(R.string.no) { dialog, which ->

            }
            .setPositiveButton(R.string.yes) { dialog, which ->
                historyViewModel.deleteCardInfo(card)
                historyViewModel.fillDataCardInfo()
            }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}