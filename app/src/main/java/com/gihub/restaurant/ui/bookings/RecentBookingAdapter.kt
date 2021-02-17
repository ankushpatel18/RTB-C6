package com.gihub.restaurant.ui.bookings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.Restaurant
import kotlinx.android.synthetic.main.activity_table_gallery.*
import kotlinx.android.synthetic.main.item_recent_booking.view.*
import kotlinx.android.synthetic.main.item_restaurants.view.*
import kotlinx.android.synthetic.main.item_restaurants.view.ivRestaurant
import kotlinx.android.synthetic.main.item_restaurants.view.tvAddress
import kotlinx.android.synthetic.main.item_restaurants.view.tvRestaurantName
import java.text.SimpleDateFormat


class RecentBookingAdapter(val context: Context, private var recentBookings: ArrayList<Bookings>) :
    RecyclerView.Adapter<RecentBookingAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recent_booking, p0, false)
        return BookingViewHolder(
            view
        )
    }

    override fun getItemCount() = recentBookings.size /*5*/

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        Glide.with(context)
            .load(recentBookings[position].photo)
            .apply(RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder))
            .into(holder.ivRestaurant)


        holder.tvRestaurantName.text = recentBookings[position].restaurantName
        holder.tvAddress.text = recentBookings[position].restaurantAddress
        holder.tvTable.text = context.getString(R.string.table_near)+ recentBookings[position].location
        holder.tvTime.text = SimpleDateFormat("dd/MMM/yyyy  hh:mm a").format(recentBookings[position].time)
    }


    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivRestaurant = view.ivRestaurant!!
        val tvRestaurantName = view.tvRestaurantName!!
        val tvAddress = view.tvAddress!!
        val tvTime = view.tvTime!!
        val tvTable = view.tvTable!!

    }
}