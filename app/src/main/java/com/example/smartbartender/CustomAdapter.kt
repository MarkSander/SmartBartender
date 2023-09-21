package com.example.smartbartender
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat

class CustomAdapter(private val context: Context, private val cocktails: List<CocktailInterface>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return cocktails.size
    }

    override fun getItem(position: Int): Any {
        return cocktails[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.activity_gridview, null)
        }

        val icon = view!!.findViewById<ImageView>(R.id.drinkGridImage)
        val drinkText = view!!.findViewById<TextView>(R.id.drinkGridText)

        icon.setImageResource(cocktails[position].imageResourceId)
        drinkText.text = cocktails[position].name


        return view
    }
}
