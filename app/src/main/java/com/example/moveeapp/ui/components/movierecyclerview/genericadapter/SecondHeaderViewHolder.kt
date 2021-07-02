package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.View
import android.widget.TextView
import com.example.moveeapp.R

/**
 * Created by Ecem Suren on 18.10.2020.
 */
class SecondHeaderViewHolder(itemView : View, val model: SecondHeader): BaseViewHolder(itemView) {

    val headerTextView: TextView = itemView.findViewById(R.id.header_text)

    override fun bindItems() {

        headerTextView.text = model.title


    }
}