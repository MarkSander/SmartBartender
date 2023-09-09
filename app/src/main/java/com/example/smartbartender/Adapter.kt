package com.example.smartbartender

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val drklist: List<CocktailInterface>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var listener: OnItemClickListener? = null
    // This method creates a new ViewHolder object for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.drinks_list, parent, false)
        return MyViewHolder(itemView)
    }

    // This method returns the total
    // number of items in the data set
    override fun getItemCount(): Int {
        return drklist.size
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentDrk = drklist[position]
        holder.name.text = currentDrk.name
        val ingredientsString = currentDrk.ingredients.entries.joinToString(", "){ "${it.key}: ${it.value} ML" }
        holder.ingredients.text = ingredientsString
        //add images to the list
        holder.image.setImageResource(currentDrk.imageResourceId)

        // Check the ingredients here and enable/disable the button
        val enableButton = checkIngredients(currentDrk.ingredients)
        holder.button.isEnabled = enableButton

        // Set a click listener for the button
/*        holder.button.setOnClickListener {
            //listener?.onButtonClick(position)
            onItemClick(currentDrk)
        }*/
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun checkIngredients(ingredients: Map<String, Int>): Boolean {
        // Add your logic here to check ingredients
        // Return true if the button should be enabled, false if it should be disabled
        return true // Example logic
    }



    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val ingredients: TextView = itemView.findViewById(R.id.tvIngredients)
        val image: ImageView = itemView.findViewById(R.id.drinkImageView)
        val button: Button = itemView.findViewById(R.id.rowButton)
    }
}