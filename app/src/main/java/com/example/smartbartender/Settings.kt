package com.example.smartbartender

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels // Import this line
//import androidx.activity.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val inputDrinks = listOf("Empty", "Whiskey", "Rum", "Gin", "Pure ethanol", "Vodka")
private var selectedDrinkPosition1 = 0 // Default to the first item
private var selectedDrinkPosition2 = 0 // Default to the first item
private var selectedDrinkPosition3 = 0 // Default to the first item
private var selectedDrinkPosition4 = 0 // Default to the first item
//val drinksViewModel: DrinksViewModel by viewModels()
var drinkInput1 = "Empty"
var drinkInput2 = "Empty"
var drinkInput3 = "Empty"
var drinkInput4 = "Empty"


/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val drinksViewModel by lazy {
        ViewModelProvider(requireActivity()).get(DrinksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerDrinks1: Spinner = view.findViewById(R.id.drinkInputSpinner1)
        val spinnerDrinks2: Spinner = view.findViewById(R.id.drinkInputSpinner2)
        val spinnerDrinks3: Spinner = view.findViewById(R.id.drinkInputSpinner3)
        val spinnerDrinks4: Spinner = view.findViewById(R.id.drinkInputSpinner4)


        spinnerDrinks1?.let { spinner ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                inputDrinks
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerDrinks1.adapter = adapter

            spinner.setSelection(selectedDrinkPosition1)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedDrinkPosition1 = position
                    drinkInput1 = inputDrinks[position]
                    drinksViewModel.drinkInput1 = drinkInput1
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }
        }

        spinnerDrinks2?.let { spinner ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                inputDrinks
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerDrinks2.adapter = adapter

            spinner.setSelection(selectedDrinkPosition2)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedDrinkPosition2 = position
                    drinkInput2 = inputDrinks[position]
                    drinksViewModel.drinkInput2 = drinkInput2
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }
        }

        spinnerDrinks3?.let { spinner ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                inputDrinks
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerDrinks3.adapter = adapter

            spinner.setSelection(selectedDrinkPosition3)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedDrinkPosition3 = position
                    drinkInput3 = inputDrinks[position]
                    drinksViewModel.drinkInput3 = drinkInput3
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }
        }

        spinnerDrinks4?.let { spinner ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                inputDrinks
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerDrinks4.adapter = adapter

            spinner.setSelection(selectedDrinkPosition4)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedDrinkPosition4 = position
                    drinkInput4 = inputDrinks[position]
                    drinksViewModel.drinkInput4 = drinkInput4
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }
        }


    }

/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SpinnerItemSelectedListener) {
            spinnerItemSelectedListener = context
        } else {
            throw RuntimeException("$context must implement SpinnerItemSelectedListener")
        }
    }
*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

/*
interface SpinnerItemSelectedListener {
    fun onSpinnerItemSelected(selectedItem: String)
}*/
