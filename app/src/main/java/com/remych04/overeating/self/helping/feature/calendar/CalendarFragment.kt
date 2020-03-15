package com.remych04.overeating.self.helping.feature.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.remych04.overeating.self.helping.databinding.CalendarFragmentBinding
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: CalendarFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalendarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).title = "Здарова"
        binding.calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val date = Date()
            Toast.makeText(context, "ДАТА: $i || $i2  || $i3", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun getInstance() = CalendarFragment()
    }
}
