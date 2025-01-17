package com.example.ddi_tarea7

import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ddi_tarea7.data.local.LocalImages
import com.example.ddi_tarea7.databinding.ActivityMainBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.FullScreenCarouselStrategy

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var favoriteC = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy(), RecyclerView.HORIZONTAL)
        val images = LocalImages().getListOfImages()
        val adapter = CarouselAdapter(images)
        recyclerView.adapter = adapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false

        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            //Establecer el color de la barra de estado, solo porque me lo detectaba en color blanco y no se distinguía con el fondo claro.
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            // Escucha cambios en el scroll (La AppToolBar)
            nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY > 300) {
                    // Aparece la Toolbar cuando se hace scroll
                    if (topAppBar.visibility != View.VISIBLE) {
                        topAppBar.visibility = View.VISIBLE
                        topAppBar.animate()
                            .translationY(0f)
                            .alpha(1f)
                            .setDuration(300)
                            .start()
                    }
                } else {
                    // Oculta la Toolbar cuando se alcanca el tope
                    if (topAppBar.visibility == View.VISIBLE) {
                        topAppBar.animate()
                            .translationY(-topAppBar.height.toFloat())
                            .alpha(0f)
                            .setDuration(300)
                            .withEndAction {
                                topAppBar.visibility = View.GONE
                            }
                            .start()
                    }
                }
            }
            favorite.setOnClickListener {
                if (favoriteC) {
                    favoriteC = false
                    //Si no se ñade este texto que equivale a los likes no se desactualiza el texto
                    favorite.text = "34"

                    favorite.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_favorite_border_24,
                        0,
                        0,
                        0
                    )
                    favorite.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this@MainActivity, R.color.md_theme_scrim)
                } else {
                    favoriteC = true
                    favorite.text = "35"
                    favorite.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_favorite_24,
                        0,
                        0,
                        0
                    )
                    favorite.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this@MainActivity, R.color.md_theme_error)
                }
            }


            buttonOffer.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón Hacer Oferta", Toast.LENGTH_SHORT)
                    .show()
            }
            buttonBuy.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón Comprar", Toast.LENGTH_SHORT).show()
            }
            buttonSend.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón Enviar", Toast.LENGTH_SHORT).show()
            }
            mas.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón Más Pulsado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}