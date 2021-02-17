package com.gihub.restaurant.ui.restaurants

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.Restaurant
import kotlinx.android.synthetic.main.item_restaurants.view.*


class RestaurantListAdapter(val context: Context, private var restaurants: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

    private lateinit var onRestaurantClicked: OnItemClicked

    fun setOnRowClicked(onItemClicked: OnItemClicked) {
        this.onRestaurantClicked = onItemClicked
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_restaurants, p0, false)
        return RestaurantViewHolder(
            view,
            onRestaurantClicked,
            restaurants
        )
    }

    override fun getItemCount() = restaurants.size /*5*/

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        Glide.with(context)
            .load(restaurants[position].photo)
            .apply(RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder))
            .into(holder.ivRestaurant)


        holder.tvRestaurantName.text = restaurants[position].name
        holder.tvAddress.text = restaurants[position].address
        holder.tvCuisine.text = restaurants[position].cuisineType
    }


    class RestaurantViewHolder(view: View, onItemClicked: OnItemClicked,
                               private var restaurants: ArrayList<Restaurant>) : RecyclerView.ViewHolder(view) {
        val ivRestaurant = view.ivRestaurant!!
        val tvRestaurantName = view.tvRestaurantName!!
        val tvAddress = view.tvAddress!!
        val tvCuisine = view.tvCuisine!!

        init {
            view.setOnClickListener {
                onItemClicked.onRestaurantClicked(restaurants[adapterPosition])
            }
        }
    }

    interface OnItemClicked {
        fun onRestaurantClicked(restaurant: Restaurant)
    }
}