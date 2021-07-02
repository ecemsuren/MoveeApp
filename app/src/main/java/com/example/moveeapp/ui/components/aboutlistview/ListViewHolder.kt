package com.example.moveeapp.ui.components.aboutlistview

import android.view.View
import android.widget.TextView
import com.example.moveeapp.R

/**
 * Created by Ecem Suren on 12.11.2020.
 */
class ListViewHolder(row: View?) {
     val titletext: TextView = row?.findViewById(R.id.list_view_licences_title) as TextView
     val copyrighting: TextView = row?.findViewById(R.id.list_view_licences_copyright) as TextView
     val githubLink: TextView = row?.findViewById(R.id.list_view_licences_github_link) as TextView
     val subTitletext: TextView = row?.findViewById(R.id.list_view_licences_subtitle) as TextView
     val link: TextView = row?.findViewById(R.id.list_view_licences_link) as TextView

}