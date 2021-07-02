package com.example.moveeapp.ui.components.aboutlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.moveeapp.R


/**
 * Created by Ecem Suren on 12.11.2020.
 */
class ListViewAdapter(val context: Context, val license: ArrayList<Licenses>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View?
        var viewHolder: ListViewHolder

        if(convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.list_view_item_licenses, parent, false)
            viewHolder =
                ListViewHolder(
                    view
                )
            view.tag = viewHolder
        } else{
            view = convertView
            viewHolder = view.tag as ListViewHolder
        }

        var licences: Licenses = getItem(position) as Licenses
        viewHolder.titletext.text = licences.title
        viewHolder.copyrighting.text = licences.copyright
        viewHolder.githubLink.text = licences.githubLink
        viewHolder.subTitletext.text = licences.subtitle
        viewHolder.link.text = licences.link

        return view as View
    }

    override fun getItem(position: Int): Any {
        return license[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return license.count()
    }
}