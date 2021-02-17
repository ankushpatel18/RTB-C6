package com.gihub.restaurant.ui.tables

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.data.model.Restaurant
import kotlinx.android.synthetic.main.item_restaurants.view.*
import kotlinx.android.synthetic.main.item_table.view.*


class TableListAdapter(val context: Context, private var tables: ArrayList<DinePoint>) :
    RecyclerView.Adapter<TableListAdapter.TableViewHolder>() {

    private lateinit var onRestaurantClicked: OnItemClicked

    fun setOnRowClicked(onItemClicked: OnItemClicked) {
        this.onRestaurantClicked = onItemClicked
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TableViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_table, p0, false)
        return TableViewHolder(
            view,
            onRestaurantClicked,
            tables
        )
    }

    override fun getItemCount() = tables.size /*5*/

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        Glide.with(context)
            .load(tables[position].photo)
            .apply(RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder))
            .into(holder.ivTable)

        holder.tvTableLocation.text = tables[position].location
        holder.tvTableComment.text = tables[position].comment
    }


    class TableViewHolder(view: View, onItemClicked: OnItemClicked,
                               private var tables: ArrayList<DinePoint>) : RecyclerView.ViewHolder(view) {
        val ivTable = view.ivTable!!
        val tvTableComment = view.tvTableComment!!
        val tvTableLocation = view.tvTableLocation!!
        init {
            view.setOnClickListener {
                onItemClicked.onTableSelected(tables[adapterPosition])
            }
        }
    }

    interface OnItemClicked {
        fun onTableSelected(selectedTable: DinePoint)
    }
}