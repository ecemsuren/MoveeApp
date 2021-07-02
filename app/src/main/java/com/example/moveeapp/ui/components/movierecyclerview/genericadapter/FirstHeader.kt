package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.View
import com.example.moveeapp.R

/**
 * Created by Ecem Suren on 18.10.2020.
 */
data class FirstHeader (override val position: Int, override val title: String, val type: Int): BaseRecyclerviewItem(), BaseHeaderModel {
    override fun getHolder(view: View): BaseViewHolder {
        return FirstHeaderViewHolder(view, this)
    }

    override fun getLayout(): Int {
        return R.layout.first_header
    }
}
