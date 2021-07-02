package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.moveeapp.R

/**
 * Created by Ecem Suren on 18.10.2020.
 */
class FirstHeaderViewHolder(itemView : View, val model: FirstHeader): BaseViewHolder(itemView) {

    val firstHeader = itemView.findViewById<TextView>(R.id.header_text)
    val mapView = itemView.findViewById<ImageView>(R.id.img_map)

    override fun bindItems() {
        firstHeader.text = model.title
        mapView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }
    }
}