package com.example.moveeapp.ui.components.movierecyclerview.util

import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.moveeapp.ui.base.UIConstants


/**
 * Created by Ecem Suren on 25.09.2020.
 */

class RecyclerViewItemDecoration(listener: StickyHeaderInterface) :
    ItemDecoration() {
    private val mListener: StickyHeaderInterface
    private var mStickyHeaderHeight = UIConstants.ITEM_DECORATION_STICKY_HEADER_HEIGHT


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val topChild = parent.getChildAt(0) ?: return
        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }
        val headerPos = mListener.getHeaderPositionForItem(topChildPosition)
        val currentHeader = getHeaderViewForItem(headerPos, parent)
        fixLayoutSize(parent, currentHeader)
        val contactPoint = currentHeader.bottom
        val childInContact = getChildInContact(parent, contactPoint, headerPos)
        if (childInContact != null && mListener.isHeader(
                parent.getChildAdapterPosition(
                    childInContact
                )
            )
        ) {
            moveHeader(c, currentHeader, childInContact)
            return
        }
        drawHeader(c, currentHeader)
    }

    private fun getHeaderViewForItem(headerPosition: Int, parent: RecyclerView): View {
        val layoutResId = mListener.getHeaderLayout(headerPosition)
        val header =
            LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        mListener.bindHeaderData(header, headerPosition)
        return header
    }

    private fun drawHeader(c: Canvas, header: View) {
        c.save()
        c.translate(UIConstants.DRAW_HEADER_DX, UIConstants.DRAW_HEADER_DY)
        header.draw(c)
        c.restore()
    }

    private fun moveHeader(
        c: Canvas,
        currentHeader: View,
        nextHeader: View
    ) {
        c.save()
        c.translate(UIConstants.MOVE_HEADER_DX, nextHeader.top - currentHeader.height.toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun getChildInContact(
        parent: RecyclerView,
        contactPoint: Int,
        currentHeaderPos: Int
    ): View? {
        var childInContact: View? = null
        for (i in UIConstants.ZERO until parent.childCount) {
            var heightTolerance = UIConstants.GET_CHILD_HEIGHT_TOLERANCE
            val child = parent.getChildAt(i)

            //measure height tolerance with child if child is another header
            if (currentHeaderPos != i) {
                val isChildHeader =
                    mListener.isHeader(parent.getChildAdapterPosition(child))
                if (isChildHeader) {
                    heightTolerance = mStickyHeaderHeight - child.height
                }
            }

            //add heightTolerance if child top be in display area
            var childBottomPosition: Int
            childBottomPosition = if (child.top > UIConstants.ZERO) {
                child.bottom + heightTolerance
            } else {
                child.bottom
            }
            if (childBottomPosition > contactPoint) {
                if (child.top <= contactPoint) {
                    // This child overlaps the contactPoint
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

    /**
     * Properly measures and layouts the top sticky header.
     * @param parent ViewGroup: RecyclerView in this case.
     */
    private fun fixLayoutSize(parent: ViewGroup, view: View) {

        // Specs for parent (RecyclerView)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            parent.width,
            View.MeasureSpec.EXACTLY
        )
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            parent.height,
            View.MeasureSpec.UNSPECIFIED
        )

        // Specs for children (headers)
        val childWidthSpec = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeightSpec = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidthSpec, childHeightSpec)
        view.layout(
            UIConstants.FIX_LAYOUT_SIZE_L,
            UIConstants.FIX_LAYOUT_SIZE_T,
            view.measuredWidth,
            view.measuredHeight.also { mStickyHeaderHeight = it }
        )
    }


    init {
        mListener = listener
    }
}