package com.example.moveeapp.ui.components.movierecyclerview.genericadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.components.movierecyclerview.util.StickyHeaderInterface
import com.example.moveeapp.ui.modules.main.view.MovieFragment

class GenericAdapter()
    : RecyclerView.Adapter<BaseViewHolder>(), StickyHeaderInterface {

    private var inflater: LayoutInflater? = null
    private lateinit var items: List<BaseRecyclerviewItem>
    private lateinit var secondaryHeader: String

    companion object {

        lateinit var onItemClick: OnItemClickListener

        fun setListener(listener: OnItemClickListener) {
            if (this::onItemClick.isInitialized) {
                onItemClick = listener
            }
        }
    }
    constructor(
        mItemClick: OnItemClickListener,
        mItems: List<BaseRecyclerviewItem>
    ) : this() {

        onItemClick = mItemClick
        items = mItems


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(items[viewType].getLayout(), parent, false)
        return items[viewType].getHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindItems()

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun addData(newList: List<BaseRecyclerviewItem>) {
        (items as ArrayList).addAll(newList)
         notifyDataSetChanged()
    }
    fun clearAndAddData(searchList: List<BaseRecyclerviewItem>) {
        (items as ArrayList).clear()
        (items as ArrayList).addAll(searchList)
        notifyDataSetChanged()
    }

    fun clearList(searchCancelList : List<BaseRecyclerviewItem>){
        (items as ArrayList).clear()
        notifyDataSetChanged()
    }

        override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = UIConstants.HEADER_POSITON
        var itPos = itemPosition
        do {
            if (this.isHeader(itPos)) {
                headerPosition = itPos
                break
            }
            itPos -= UIConstants.ONE
        } while (itPos >= UIConstants.ZERO)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return items[headerPosition].getLayout()
    }

    override fun bindHeaderData(itemView: View?, headerPosition: Int) {
        val myHeader = itemView?.findViewById<TextView>(R.id.header_text)
        val layout = itemView?.findViewById<LinearLayout>(R.id.map_linearLayout)
        val mapImage = itemView?.findViewById<ImageView>(R.id.img_map)


        if(headerPosition == UIConstants.ZERO){
            if ((items[headerPosition] as FirstHeader).type == UIConstants.ZERO) {

                myHeader?.text = UIConstants.MOVIES_HEADER
                secondaryHeader = UIConstants.POPULAR_MOVIES
            } else {
                myHeader?.text = UIConstants.TV_SERIES
                secondaryHeader = UIConstants.TOP_RATED_SERIES
                layout?.visibility = View.GONE
                mapImage?.visibility = View.GONE
            }

        } else{

            if (myHeader != null) {
                setUpHeaderText(myHeader, secondaryHeader)
            }
        }
    }

    override fun isHeader(itemPosition: Int): Boolean {
        for (section in MovieFragment.sectionList) {
            if (section.position == itemPosition) {
                return true
            }
        }
        return false
    }

    fun setUpHeaderText(myHeader: TextView, headerText: String){
        myHeader.text = headerText

        myHeader.setTextAppearance(R.style.sticky_header_style)

        myHeader.setBackgroundColor(MoveeApplication.appContext.getColor(R.color.vibrant_blue))
        myHeader.gravity = UIConstants.STICKY_HEADER_GRAVITY
        val params: ConstraintLayout.LayoutParams =
            myHeader.layoutParams as ConstraintLayout.LayoutParams
        params.height = UIConstants.STICKY_HEADER_HEIGHT
        params.setMargins(
            params.leftMargin,
            UIConstants.STICKY_MARGIN_TOP_MARGIN,
            UIConstants.STICKY_MARGIN_RIGHT_MARGIN,
            params.bottomMargin
        )
        myHeader.layoutParams = params
        myHeader.setPadding(
            UIConstants.STICKY_PADDING_LEFT_MARGIN,
            UIConstants.STICKY_PADDING_TOP_MARGIN,
            UIConstants.STICKY_PADDING_RIGHT_MARGIN,
            UIConstants.STICKY_PADDING_BOTTOM_MARGIN
        )
    }



}