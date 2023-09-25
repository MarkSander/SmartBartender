package com.example.smartbartender


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbartender.drinks.AmarettoSour
import com.example.smartbartender.drinks.Appletini
import com.example.smartbartender.drinks.Bellini
import com.example.smartbartender.drinks.BloodyMary
import com.example.smartbartender.drinks.Coke
import com.example.smartbartender.drinks.CubeLibre
import com.example.smartbartender.drinks.Daiquiri
import com.example.smartbartender.drinks.DarkandStormy
import com.example.smartbartender.drinks.DraculaKiss
import com.example.smartbartender.drinks.GinTonic
import com.example.smartbartender.drinks.Hardseltzer
import com.example.smartbartender.drinks.Mojito
import com.example.smartbartender.drinks.MoscowMule
import com.example.smartbartender.drinks.Paloma
import com.example.smartbartender.drinks.PinaColada
import com.example.smartbartender.drinks.PornstarMartini
import com.example.smartbartender.drinks.Screwdriver
import com.example.smartbartender.drinks.SexOnTheBeach
import com.example.smartbartender.drinks.Spritz
import com.example.smartbartender.drinks.Vesper
import com.example.smartbartender.drinks.VodkaParalyzer
import com.example.smartbartender.drinks.WhiskySour
import com.example.smartbartender.drinks.WhiskyandCoke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


/*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*/
var homeInput1 = "Empty"
var homeInput2 = "Empty"
var homeInput3 = "Empty"
var homeInput4 = "Empty"

class Home() : Fragment() {
/*    private var param1: String? = null
    private var param2: String? = null*/
    private lateinit var simpleGrid: GridView
    private lateinit var simpleCustomCocktailGrid: GridView
    private lateinit var emptyGridText: TextView
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    private val cocktailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CocktailViewModel::class.java)
    }
    private lateinit var clickedImageView: ImageView
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
            try {
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
                rasberryHttpRequests.sendHttpHomeViewRequestAsync()

                val filteredCocktailList: MutableList<CocktailInterface> = mutableListOf()
                for (cocktail in drinksList) {
                    val ingredients = cocktail.ingredients.keys.toList()
                    var canBeAdded = true
                    for (ing in ingredients) {
                        if (ing == homeInput1 || ing == homeInput2 || ing == homeInput3 || ing == homeInput4) {
                            Log.d("FilterCocktail", "The ingredient $ing was found in the inputs")
                        } else {
                            Log.d(
                                "Added Cocktail",
                                "${cocktail.name} could not be added to the list because of ingredient $ing"
                            )
                            canBeAdded = false
                        }
                    }
                    if (canBeAdded) {
                        filteredCocktailList.add(cocktail)
                        Log.d(
                            "Cocktail filtered",
                            "New Cocktail added to filtered list: $cocktail List now contains: $filteredCocktailList"
                        )
                    }
                }

                val customMadeDrinks = cocktailViewModel.cocktailList.toList()
                if (filteredCocktailList.isEmpty() && customMadeDrinks.isEmpty()) {
                    simpleGrid.visibility = View.GONE
                    emptyGridText.visibility = View.VISIBLE
                } else {
                    simpleGrid.visibility = View.VISIBLE
                    emptyGridText.visibility = View.GONE
                    val customAdapter = CustomAdapter(requireContext(), filteredCocktailList)
                    simpleGrid.adapter = customAdapter
                }
                Log.d(
                    "CocktailViewmodel",
                    "Cocktailviewmodel List constains the following in the home view: ${cocktailViewModel.cocktailList}"
                )
                val customMadeCocktailAdapter = CustomAdapter(requireContext(), customMadeDrinks)
                simpleCustomCocktailGrid.adapter = customMadeCocktailAdapter

                // implement setOnItemClickListener event on GridView
                simpleGrid.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, position, _ ->
                    // Handle item click here, for example, navigate to the detail view
                    val clickedDrink = filteredCocktailList[position]
                    val clickedDrinkIngredients = clickedDrink.ingredients
                    val clickedDrinkExtraIngredients = clickedDrink.extraIngredients
                    val intent = Intent(activity, DrinkDetailActivity::class.java)
                    //val intent = Intent(requireContext(), DrinkDetailActivity::class.java)


                    intent.putExtra("drinkName", clickedDrink.name)
                    intent.putExtra("drinkImageResource", clickedDrink.imageResourceId)
                    intent.putExtra("drinkIngredients", HashMap(clickedDrinkIngredients))
                    intent.putExtra("extraIngredients", HashMap(clickedDrinkExtraIngredients))
                    val imgTransition = view.findViewById<ImageView>(R.id.drinkGridImage)
                    Log.d("Imageview", "Imageview found: $imgTransition")
                    val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgTransition, "drinkImageTransition")
                    //startActivity(intent, options.toBundle())
                    startActivity(intent, options.toBundle())
                })
                simpleCustomCocktailGrid.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        // Handle item click here, for example, navigate to the detail view
                        val clickedDrink = customMadeDrinks[position]
                        val clickedDrinkIngredients = clickedDrink.ingredients
                        val clickedDrinkExtraIngredients = clickedDrink.extraIngredients
                        val intent = Intent(requireContext(), DrinkCustomDetailActivity::class.java)

                        intent.putExtra("drinkName", clickedDrink.name)
                        intent.putExtra("drinkImageResource", clickedDrink.imageResourceId)
                        intent.putExtra("drinkIngredients", HashMap(clickedDrinkIngredients))
                        intent.putExtra("extraIngredients", HashMap(clickedDrinkExtraIngredients))
                        intent.putExtra("positionInList", position)
                        //startActivity(intent, options.toBundle())
                        startActivity(intent)
                    }
            } catch (e: Exception){
                Log.e("HTTP Error", "Cant connect to the rasberry")
                displayServerError()
            }
        }
    }

    private fun displayServerError() {
        // This function is called when there's a server error
        // You can update your UI to display an error message
        simpleGrid.visibility = View.GONE
        simpleCustomCocktailGrid.visibility = View.GONE
        emptyGridText.text = "Server is down. Please try again later."
        emptyGridText.visibility = View.VISIBLE
    }

    private fun sendHttpHomeViewRequestAsync() {
        val url = "http://192.168.233.144:5000/pumprequest/"

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
            homeInput1 = jsonObject.optString("pump1drink", "Empty")
            homeInput2 = jsonObject.optString("pump2drink", "Empty")
            homeInput3 = jsonObject.optString("pump3drink", "Empty")
            homeInput4 = jsonObject.optString("pump4drink", "Empty")

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