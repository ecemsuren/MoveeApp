package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ecem Suren on 28.09.2020.
 */

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view){
    abstract fun bindItems()
}