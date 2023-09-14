package com.example.smartbartender

import android.content.Context
import android.content.Intent
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
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val inputDrinks = listOf("Empty", "Whisky", "Rum", "Gin", "Amarreto", "Vodka", "Ginger Beer", "Maple Syrup", "Prosecco", "Coke", "Tonic", "Sparkling Water", "Tequilla", "Passoa", "Safari", "Orange Juice", "Aperol")
private var selectedDrinkPosition1 = 0 // Default to the first item
private var selectedDrinkPosition2 = 0 // Default to the first item
private var selectedDrinkPosition3 = 0 // Default to the first item
private var selectedDrinkPosition4 = 0 // Default to the first item
//val drinksViewModel: DrinksViewModel by viewModels()
var drinkInput1 = ""
var drinkInput2 = ""
var drinkInput3 = ""
var drinkInput4 = ""


/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    // Access appPreferences in your fragment using requireContext()
    private lateinit var appPreferences: AppPreferences


    /*    private val drinksViewModel by lazy {
        ViewModelProvider(requireActivity()).get(DrinksViewModel::class.java)
    }*/
    //var drinksViewModel = ViewModelProvider(requireActivity()).get(DrinksViewModel::class.java)
    private lateinit var drinksViewModel: DrinksViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Access appPreferences once the fragment is attached
        appPreferences = (requireContext().applicationContext as MyApplication).appPreferences
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
        //return inflater.inflate(R.layout.fragment_settings, container, false)
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        drinksViewModel = ViewModelProvider(requireActivity()).get(DrinksViewModel::class.java) // Initialize here

        // ... (rest of your code)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerDrinks1: Spinner = view.findViewById(R.id.drinkInputSpinner1)
        val spinnerDrinks2: Spinner = view.findViewById(R.id.drinkInputSpinner2)
        val spinnerDrinks3: Spinner = view.findViewById(R.id.drinkInputSpinner3)
        val spinnerDrinks4: Spinner = view.findViewById(R.id.drinkInputSpinner4)
        val intent = Intent(requireContext(), DrinkDetailActivity::class.java)
        val flush1 = view.findViewById<Button>(R.id.flush1)
        val flush2 = view.findViewById<Button>(R.id.flush2)
        val flush3 = view.findViewById<Button>(R.id.flush3)
        val flush4 = view.findViewById<Button>(R.id.flush4)
        val fill1 = view.findViewById<Button>(R.id.fill1)
        val fill2 = view.findViewById<Button>(R.id.fill2)
        val fill3 = view.findViewById<Button>(R.id.fill3)
        val fill4 = view.findViewById<Button>(R.id.fill4)
        sendHttpRequestAsync()

        flush1.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(10,0,0,0)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }

        flush2.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(0,10,0,0)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
        flush3.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(0,0,10,0)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
        flush4.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(0,0,0,10)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
        fill1.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(2,0,0,0)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
        fill2.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(0,2,0,0)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
        fill3.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(0,0,2,0)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
        fill4.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendFillAndFlushHttpRequestAsync(0,0,0,2)
                if (result == "Finished") {
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }
            }
        }
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
                    sendNewPumpHttpRequestAsync(drinkInput1, drinkInput2, drinkInput3, drinkInput4)
                    //appPreferences.saveDrinkInput("drinkInput1", drinkInput1)
                    //drinksViewModel.drinkInput1.value = drinkInput1
                    Log.d("SettingsFragment", "drinkInput1 updated to: ${appPreferences.getDrinkInput("drinkInput1", "Empty")}")
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
                    sendNewPumpHttpRequestAsync(drinkInput1, drinkInput2, drinkInput3, drinkInput4)
                    //appPreferences.saveDrinkInput("drinkInput2", drinkInput2)
                    //drinksViewModel.drinkInput2.value = drinkInput2
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
                    sendNewPumpHttpRequestAsync(drinkInput1, drinkInput2, drinkInput3, drinkInput4)
                    //appPreferences.saveDrinkInput("drinkInput3", drinkInput3)
                    //drinksViewModel.drinkInput3.value = drinkInput3
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
                    sendNewPumpHttpRequestAsync(drinkInput1, drinkInput2, drinkInput3, drinkInput4)
                    //appPreferences.saveDrinkInput("drinkInput4", drinkInput4)
                    //drinksViewModel.drinkInput4.value = drinkInput4
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
    private fun sendHttpRequestAsync() {
    val url = "http://192.168.68.74:5000/pumprequest/"

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
                Log.d("HttpRequest", "Response body received: $responseBody")

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
        val jsonObject= JSONObject(result)
        drinkInput1 = jsonObject.optString("pump1drink", "Empty")
        selectedDrinkPosition1 = inputDrinks.indexOf(drinkInput1)
        drinkInput2 = jsonObject.optString("pump2drink", "Empty")
        selectedDrinkPosition2 = inputDrinks.indexOf(drinkInput2)
        drinkInput3 = jsonObject.optString("pump3drink", "Empty")
        selectedDrinkPosition3 = inputDrinks.indexOf(drinkInput3)
        drinkInput4 = jsonObject.optString("pump4drink", "Empty")
        selectedDrinkPosition4 = inputDrinks.indexOf(drinkInput4)

    }
}
    private fun sendNewPumpHttpRequestAsync(pump1: String, pump2: String, pump3: String, pump4: String) {
        val url = "http://192.168.68.74:5000/pumpchange/"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val json = buildJsonObject {
                    put("pump1", pump1)
                    put("pump2", pump2)
                    put("pump3", pump3)
                    put("pump4", pump4)
                }

/*                val json = """
        {
            "pump1": $pump1,
            "pump2": $pump2,
            "pump3": $pump3,
            "pump4": $pump4
        }
    """.trimIndent()*/
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json")
                    .post(requestBody)
                    .build()

                try {
                    val response = client.newCall(request).execute()
                    // Handle the response as needed
                    val responseBody = response.body?.string()
                    // Process the response data here
                    Log.e("HttpRequest", "Response body received: $responseBody")

                    // Return the result if needed
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        // Process the response body
                    } else {
                        // Handle the error
                        println("HTTP Error: ${response.code}")
                        println("Error Message: ${response.body?.string()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle the exception
                    null // Return null in case of an error
                }
            }

            // You can use await to get the result later if needed
            val result = deferredResult.await()

        }
    }

    private fun sendFillAndFlushHttpRequestAsync(pump1: Int, pump2: Int, pump3: Int, pump4: Int): String? {
        val url = "http://192.168.68.74:5000/$pump1/$pump2/$pump3/$pump4"

        // Use runBlocking to launch a coroutine for the network request
        return runBlocking {
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
            result
        }
    }

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
