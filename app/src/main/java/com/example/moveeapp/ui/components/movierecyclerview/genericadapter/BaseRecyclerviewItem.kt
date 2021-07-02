package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.View

abstract class BaseRecyclerviewItem:  IBaseRecyclerviewItem{

    abstract fun getHolder(view: View): BaseViewHolder
    abstract fun getLayout(): Int

}