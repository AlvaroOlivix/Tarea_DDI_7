package com.example.ddi_tarea7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CarouselAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.carousel_image_view)

        fun bind(image: Int) {
            Glide.with(itemView.context).load(image).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_carousel, viewGroup, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CarouselViewHolder, position: Int) {
        viewHolder.bind(images[position])
    }

    override fun getItemCount() = images.size

}