package com.gihub.restaurant.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.DinePoint
import kotlinx.android.synthetic.main.item_gallery.view.*
import kotlinx.android.synthetic.main.item_table.view.*


class GalleryListAdapter(val context: Context, private var pictures: ArrayList<String>) :
    RecyclerView.Adapter<GalleryListAdapter.PictureViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PictureViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gallery, p0, false)
        return PictureViewHolder(
            view
        )
    }

    override fun getItemCount() = pictures.size /*5*/

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        Glide.with(context)
            .load(pictures[position])
            .apply(RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder))
            .into(holder.ivImage)
    }


    class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage = view.ivImage!!
    }

}