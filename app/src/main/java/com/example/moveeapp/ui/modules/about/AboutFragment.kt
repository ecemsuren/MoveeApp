package com.example.moveeapp.ui.modules.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moveeapp.R
import com.example.moveeapp.ui.components.aboutlistview.Licenses
import com.example.moveeapp.ui.components.aboutlistview.ListViewAdapter

class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val listView = view?.findViewById<ListView>(R.id.licenses_list_view)
        val listLicences: ArrayList<Licenses> = ArrayList()

        listLicences.add(
            Licenses(
                resources.getString(R.string.rx_java),
                resources.getString(R.string.rx_java_copyright),
                resources.getString(R.string.rx_java_github_link),
                resources.getString(R.string.subtitle),
                resources.getString(R.string.apache_link)
            )
        )

        listLicences.add(
            Licenses(
                resources.getString(R.string.rx_binding),
                resources.getString(R.string.rx_binding_copyright),
                resources.getString(R.string.rx_binding_github_link),
                resources.getString(R.string.subtitle),
                resources.getString(R.string.apache_link)
            )
        )

        listLicences.add(
            Licenses(
                resources.getString(R.string.lottie),
                resources.getString(R.string.lottie_copyright),
                resources.getString(R.string.lottie_github_link),
                resources.getString(R.string.subtitle),
                resources.getString(R.string.apache_link)
            )
        )

        listLicences.add(
            Licenses(
                resources.getString(R.string.retrofit),
                resources.getString(R.string.retrofit_copyright),
                resources.getString(R.string.retrofit_github_link),
                resources.getString(R.string.subtitle),
                resources.getString(R.string.apache_link)
            )
        )

        listLicences.add(
            Licenses(
                resources.getString(R.string.glide),
                resources.getString(R.string.glide_copyright),
                resources.getString(R.string.glide_github_link),
                resources.getString(R.string.subtitle),
                resources.getString(R.string.apache_link)
            )
        )


        listView?.adapter =
            ListViewAdapter(
                requireActivity(),
                listLicences
            )
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickToTheLicenses()
    }

    fun clickToTheLicenses() {
        val licenses = view?.findViewById<TextView>(R.id.licenses)
        val licensesListView = view?.findViewById<ListView>(R.id.licenses_list_view)
        var isClick = true

        licenses?.setOnClickListener {
            if (isClick) {
                licensesListView?.visibility = View.VISIBLE
                isClick = false
            } else {
                licensesListView?.visibility = View.GONE
                isClick = true
            }


        }

    }
}