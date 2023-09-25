package com.example.smartbartender

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private val inputDrinks = listOf("Empty", "Whisky", "Rum", "Gin", "Amaretto", "Vodka", "Ginger Beer", "Maple Syrup", "Prosecco", "Coke", "Tonic", "Sparkling Water", "Tequilla", "Passoa", "Safari", "Orange Juice", "Aperol")

class Profile : Fragment(){
    // TODO: Rename and change types of parameters

    private lateinit var cocktailNameEditText: EditText
    private lateinit var ingredientsEditText: EditText
    private lateinit var extraIngredientsEditText: EditText
    private lateinit var createCocktailButton: Button
    //val cocktailViewModel: CocktailViewModel by viewModels()
    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_addcocktail, container, false)
        val drinkName = view.findViewById<EditText>(R.id.drinkTitleInput)
        val amount1 = view.findViewById<EditText>(R.id.ingredient1Amount)
        val amount2 = view.findViewById<EditText>(R.id.ingredient2Amount)
        val amount3 = view.findViewById<EditText>(R.id.ingredient3Amount)
        val amount4 = view.findViewById<EditText>(R.id.ingredient4Amount)
        amount1.filters = arrayOf(InputFilterMinMax("0", "200"))
        amount2.filters = arrayOf(InputFilterMinMax("0", "200"))
        amount3.filters = arrayOf(InputFilterMinMax("0", "200"))
        amount4.filters = arrayOf(InputFilterMinMax("0", "200"))

        cocktailViewModel = ViewModelProvider(requireActivity()).get(CocktailViewModel::class.java)

        val sendRequestButton = view.findViewById<Button>(R.id.addConfirmButton)
        sendRequestButton.setOnClickListener {
            // Handle button click and send the request here
            val ingredients: MutableMap<String, Int> = HashMap()
            val extraIngredients: MutableMap<String, Int> = HashMap()
            val am1: String = amount1.text.toString()
            val am2: String = amount2.text.toString()
            val am3: String = amount3.text.toString()
            val am4: String = amount4.text.toString()
            Log.d("Drink input", "Drink input1: $drinkInput1")
            if (am1 != ""){
                ingredients[drinkInput1] = Integer.parseInt(am1)
            }
            if (am2 != ""){
                ingredients[drinkInput2] = Integer.parseInt(am2)
            }
            if (am3 != ""){
                ingredients[drinkInput3] = Integer.parseInt(am3)
            }
            if (am4 != ""){
                ingredients[drinkInput4] = Integer.parseInt(am4)
            }
            val newCocktail = Cocktail(drinkName.text.toString(), ingredients, extraIngredients, R.drawable.defaultdrinkimage)
            val isAdded = cocktailViewModel.addNewCocktail(newCocktail)
            if (isAdded){
                val succesFragment = SuccesfullDialogFragment()
                succesFragment.show(requireActivity().supportFragmentManager, "succes_dialog")
            } else {
                val errorDialog = ErrorDialogFragment()
                errorDialog.show(requireActivity().supportFragmentManager, "error_dialog")
            }

            Log.d("CocktailViewmodel info", "New cocktail added in the list: ${cocktailViewModel.cocktailList}")
        }

        return view
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

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    drinkInput1 = inputDrinks[position]
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

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    drinkInput2 = inputDrinks[position]
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

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    drinkInput3 = inputDrinks[position]
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

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    drinkInput4 = inputDrinks[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }
        }

    }

    private fun sendHttpRequestAsync() {
        val url = "http://192.168.234.144:5000/pump1"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()

                try {
                    val response = client.newCall(request).execute()
                    // Handle the response as needed
                    val responseBody = response.body?.string()
                    // Process the response data here

                    // Return the result if needed
                    responseBody
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle the exception
                    null // Return null in case of an error
                }
            }

            // You can use await to get the result later if needed
            val result = deferredResult.await()
            // Process the result or use it as needed
        }
    }

}

class ErrorDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext(), R.style.RedErrorDialogTheme)
        return builder
            .setTitle("Error")
            .setMessage("Total quantity exceeds 200. Cocktail not added.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}

class SuccesfullDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext(), R.style.SuccesDialogTheme)
        return builder
            .setTitle("Succes")
            .setMessage("Your custom cocktail has been added.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(activity, MainActivity()::class.java)
                startActivity(intent)
            }
            .create()
    }

}