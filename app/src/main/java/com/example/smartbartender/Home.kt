package com.example.smartbartender

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
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
    private lateinit var simpleCustomCocktailGrid: GridView
    private lateinit var emptyGridText: TextView
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    private var input1 = "Empty"
    private var input2 = "Empty"
    private var input3 = "Empty"
    private var input4 = "Empty"
    private val cocktailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CocktailViewModel::class.java)
    }
    //val cocktailViewModel: CocktailViewModel by viewModels()


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
        simpleCustomCocktailGrid = view.findViewById(R.id.simpleCustomCocktailGridView)
        emptyGridText = view.findViewById(R.id.empty_state_message)
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
            input1 = "Empty"
            input2 = "Empty"
            input3 = "Empty"
            input4 = "Empty"

            val filteredCocktailList: MutableList<CocktailInterface> = mutableListOf()
            for (cocktail in drinksList){
                val ingredients = cocktail.ingredients.keys.toList()
                var canBeAdded = true
                for(ing in ingredients){
                    if (ing == input1 || ing == input2 || ing == input3 || ing == input4){
                        Log.d("FilterCocktail", "The ingredient $ing was found in the inputs")
                    } else {
                        canBeAdded = false
                    }
                }
                if (canBeAdded){
                    filteredCocktailList.add(cocktail)
                    Log.d("Cocktail filtered", "New Cocktail added to filterred list: $cocktail List now contais: $filteredCocktailList")
                }
            }

            val customMadeDrinks = cocktailViewModel.cocktailList.toList()
            if(filteredCocktailList.isEmpty() && customMadeDrinks.isEmpty()){
                simpleGrid.visibility = View.GONE
                emptyGridText.visibility = View.VISIBLE
            } else{
                simpleGrid.visibility = View.VISIBLE
                emptyGridText.visibility = View.GONE
                val customAdapter = CustomAdapter(requireContext(), filteredCocktailList)
                simpleGrid.adapter = customAdapter
            }
            Log.d("CocktailViewmodel", "Cocktailviewmodel List constains the following in the home view: ${cocktailViewModel.cocktailList}")
            val customMadeCocktailAdapter = CustomAdapter(requireContext(), customMadeDrinks)
            simpleCustomCocktailGrid.adapter = customMadeCocktailAdapter

            // implement setOnItemClickListener event on GridView
            simpleGrid.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, position, _ ->
                // Handle item click here, for example, navigate to the detail view
                val clickedDrink = filteredCocktailList[position]
                val clickedDrinkIngredients = clickedDrink.ingredients
                val clickedDrinkExtraIngredients = clickedDrink.extraIngredients
                val intent = Intent(requireContext(), DrinkDetailActivity::class.java)

                intent.putExtra("drinkName", clickedDrink.name)
                intent.putExtra("drinkImageResource", clickedDrink.imageResourceId)
                intent.putExtra("drinkIngredients", HashMap(clickedDrinkIngredients))
                intent.putExtra("extraIngredients", HashMap(clickedDrinkExtraIngredients))
                //startActivity(intent, options.toBundle())
                startActivity(intent)
            })
            simpleCustomCocktailGrid.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, position, _ ->
                // Handle item click here, for example, navigate to the detail view
                val clickedDrink = customMadeDrinks[position]
                val clickedDrinkIngredients = clickedDrink.ingredients
                val clickedDrinkExtraIngredients = clickedDrink.extraIngredients
                val intent = Intent(requireContext(), DrinkDetailActivity::class.java)

                intent.putExtra("drinkName", clickedDrink.name)
                intent.putExtra("drinkImageResource", clickedDrink.imageResourceId)
                intent.putExtra("drinkIngredients", HashMap(clickedDrinkIngredients))
                intent.putExtra("extraIngredients", HashMap(clickedDrinkExtraIngredients))
                //startActivity(intent, options.toBundle())
                startActivity(intent)
            })
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