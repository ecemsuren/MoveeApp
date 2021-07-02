package com.example.moveeapp.data.model.tmdb


import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.ui.components.movierecyclerview.DetailsCastRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    @SerializedName(ModelConstants.CAST_CASTID)
    val castId: Int? = null,
    @SerializedName(ModelConstants.CAST_CHARACTER)
    val character: String? =null,
    @SerializedName(ModelConstants.CAST_CREDIT_ID)
    val creditId: String?= null,
    @SerializedName(ModelConstants.CAST_GENDER)
    val gender: Int? = null,
    @SerializedName(ModelConstants.CAST_ID)
    val id: Int? = null,
    @SerializedName(ModelConstants.CAST_NAME)
    val name: String? = null,
    @SerializedName(ModelConstants.CAST_ORDER)
    val order: Int? = null,
    @SerializedName(ModelConstants.CAST_PROFILE_PATH)
    val profilePath: String? = null
) : Parcelable, BaseRecyclerviewItem() {

    override fun getHolder(view: View): BaseViewHolder {
        return DetailsCastRecyclerViewHolder(view, this)
    }

    override fun getLayout(): Int {
        return R.layout.recyclerview_item_details_cast
    }

}