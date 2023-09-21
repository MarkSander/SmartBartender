package com.example.smartbartender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.smartbartender.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var settings = Settings()
        //replaceFragment(Home(settings))
        replaceFragment(Home())
        //setContentView(R.layout.activity_main)

/*        val btn: Button = findViewById(R.id.btn_frag)

        // Set an OnClickListener on the button
        btn.setOnClickListener {
            // When the button is clicked, replace
            // the current fragment with a new
            // instance of the FirstFragment
            replaceFragment(Home())

            // Hide the button
            btn.visibility = View.GONE
        }*/

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.settings -> replaceFragment(settings)

                else ->{

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}