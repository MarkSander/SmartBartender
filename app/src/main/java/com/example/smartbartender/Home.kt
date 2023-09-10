package com.example.smartbartender

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbartender.drinks.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

import java.io.File
/*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*/

class Home(settings: Settings) : Fragment() {
/*    private var param1: String? = null
    private var param2: String? = null*/
    private lateinit var simpleGrid: GridView
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    private val drinksViewModel by lazy {
        ViewModelProvider(requireActivity()).get(DrinksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_home, container, false)
        val view = inflater.inflate(R.layout.cocktail_gridview, container, false)


        /*        val sendRequestButton = view.findViewById<Button>(R.id.recycleView.rowButton)
                sendRequestButton.setOnClickListener {
                    // Handle button click and send the request here
                    sendHttpRequestAsync()
                }*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simpleGrid = view.findViewById(R.id.simpleGridView)
        val coroutineScope = CoroutineScope(Dispatchers.Main)


        coroutineScope.launch {
            // Manually create instances of drinks
            val drinksList = listOf(
                AmarettoSour(),
                Appletini(),
                Bellini(),
                BloodyMary(),
                Coke(),
                CubeLibre(),
                Daiquiri(),
                DarkandStormy(),
                DraculaKiss(),
                GinTonic(),
                Hardseltzer(),
                Mojito(),
                MoscowMule(),
                Paloma(),
                PinaColada(),
                PornstarMartini(),
                Screwdriver(),
                SexOnTheBeach(),
                Spritz(),
                Vesper(),
                VodkaParalyzer(),
                WhiskyandCoke(),
                WhiskySour()
                // Add other drinks here...
            )

            //val itemAdapter = Adapter(drinksList)
/*                // Handle item click here, for example, navigate to the detail view
                val detailFragment = DrinkDetailFragment.newInstance(clickedDrink)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_fragment, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }*/

/*            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            recyclerView.layoutManager = LinearLayoutManager(context)

            recyclerView.adapter = itemAdapter*/


            val customAdapter = CustomAdapter(requireContext(), drinksList)
            simpleGrid.adapter = customAdapter

            // implement setOnItemClickListener event on GridView
            simpleGrid.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, position, _ ->
                // Handle item click here, for example, navigate to the detail view
                val clickedDrink = drinksList[position]
                val clickedDrinkIngredients = clickedDrink.ingredients
                val intent = Intent(requireContext(), DrinkDetailActivity::class.java)
                intent.putExtra("drinkName", clickedDrink.name)
                intent.putExtra("drinkImageResource", clickedDrink.imageResourceId)
                intent.putExtra("drinkIngredients", HashMap(clickedDrinkIngredients))
                startActivity(intent)
            })
        }
    }

    /*    companion object {
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                Home(settings).apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }*/

/*    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coroutineScope = CoroutineScope(Dispatchers.Main)

        coroutineScope.launch {
            val drinksList = withContext(Dispatchers.IO) {
                //TODO: url parameters meegeven in getDrinksData()
                var inputString = "Vodka"
                if (drinksViewModel.drinkInput1 != "Empty"){
                    inputString = drinkInput1
                }
                if (drinksViewModel.drinkInput2 != "Empty"){
                    inputString = "$inputString, $drinkInput2"
                }
                if (drinksViewModel.drinkInput3 != "Empty"){
                    inputString = "$inputString, $drinkInput3"
                }
                if (drinksViewModel.drinkInput4 != "Empty"){
                    inputString = "$inputString, $drinkInput4"
                }
                Constants.getDrinksData(inputString)
            }

            val itemAdapter = Adapter(drinksList)

            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            recyclerView.layoutManager = LinearLayoutManager(context)

            recyclerView.adapter = itemAdapter
        }
    }*/

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

    private fun RecyclerView.addOnItemClickListener(onItemClickListener: (position: Int, view: View) -> Unit) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onItemClickListener(holder.adapterPosition, view)
                }
            }
        })
    }
}