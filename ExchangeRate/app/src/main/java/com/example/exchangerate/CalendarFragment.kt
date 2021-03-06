package com.example.exchangerate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.exchangerate.databinding.FragmentCalendarBinding
import java.util.*


class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExchRateViewModel by activityViewModels()

    var selectDate = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root

    }//onCreateView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        ///// calendar /////
        val calendarView: CalendarView = binding.calendarView

        val today = Calendar.getInstance()
        calendarView.maxDate = today.timeInMillis

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.btnChooseDate.isEnabled = true
            selectDate =
                year.toString() + (month + 1).toString().padStart(2, '0') + dayOfMonth.toString()
                    .padStart(2, '0')

            Log.d("<khy> - 달력", "${year} / ${month} / ${dayOfMonth}")
            Log.d("<khy> selectdate", selectDate)
        }


        ///// btn /////
        binding.btnChooseDate.setOnClickListener {
            chooseDate()
        }
    }//onViewCreated


    fun chooseDate() {
        viewModel.dateUrl = selectDate
        val navArgument: CalendarFragmentArgs by navArgs()

        viewModel.getExchRate(viewModel.dateUrl)
        if (viewModel.boo) {
            when (navArgument.chooseFuncNum) {
                1 -> findNavController().navigate(R.id.action_calendarFragment_to_calcExchRateFragment)
                2 -> findNavController().navigate(R.id.action_calendarFragment_to_exchRateListFragment)
            }
        } else {
            Toast.makeText(requireContext(), "It's not a business day.\n" +
                    "Please choose the date again.", Toast.LENGTH_LONG).show()
            binding.btnChooseDate.isEnabled = false
        }

    }//chooseDate

}