package com.example.ddi_tarea7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ddi_tarea7.data.local.LocalImages
import com.example.ddi_tarea7.databinding.ActivityMainBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.FullScreenCarouselStrategy

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy(), RecyclerView.HORIZONTAL)
        val images = LocalImages().getListOfImages()
        val adapter = CarouselAdapter(images)
        recyclerView.adapter = adapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false

    }

    private fun setUpView() {
        binding.apply {

            buttonOffer.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón Comprar", Toast.LENGTH_SHORT).show()
            }

            buttonOffer.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón Oferta", Toast.LENGTH_SHORT).show()
            }

            buttonSend.setOnClickListener{
                Toast.makeText(this@MainActivity, "Botón Enviar", Toast.LENGTH_SHORT).show()
            }
        }
    }

}