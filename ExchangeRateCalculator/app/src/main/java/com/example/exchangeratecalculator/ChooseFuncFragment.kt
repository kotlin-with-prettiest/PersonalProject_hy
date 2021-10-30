package com.example.exchangeratecalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.exchangeratecalculator.databinding.FragmentChooseFuncBinding
import com.example.exchangeratecalculator.viewmodels.ExchRateCalcViewModel
import com.example.exchangeratecalculator.viewmodels.ExchRateCalcViewModelFactory

class ChooseFuncFragment : Fragment() {

    private var _binding: FragmentChooseFuncBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ExchRateCalcViewModel by activityViewModels {
        ExchRateCalcViewModelFactory(
            (activity?.application as ExchRateCalcApplication).database.exchangeRateDao()
        )
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseFuncBinding.inflate(inflater, container, false)
        return binding.root
    }//onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.chooseFuncFragment = this

    }//onViewCreated

    fun goExchRateFragment(){

    }
    fun goCalcFragment(){

    }
}



