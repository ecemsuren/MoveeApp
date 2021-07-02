package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.ui.components.movierecyclerview.RecyclerSectionViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener

/**
 * Created by Ecem Suren on 29.09.2020.
 */

data class RecyclerViewSection(
    val position: Int,
    val recyclerViewName: String,
    val type: Int,
    val listener: OnItemClickListener
) : BaseRecyclerviewItem() {
    override fun getHolder(view: View): BaseViewHolder {
        return RecyclerSectionViewHolder(view, type, this, listener)
    }

    override fun getLayout(): Int {
        return R.layout.carousel_recyclerview
    }
}



