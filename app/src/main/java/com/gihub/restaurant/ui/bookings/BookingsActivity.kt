package com.gihub.restaurant.ui.bookings

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.Restaurant
import com.gihub.restaurant.ui.base.BaseActivity
import com.gihub.restaurant.ui.tables.TablesActivity
import com.gihub.restaurant.utils.Constants
import com.gihub.restaurant.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_recent_bookings.*
import javax.inject.Inject

class BookingsActivity : BaseActivity<BookingViewModel>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    private lateinit var mainViewModel: BookingViewModel

    private lateinit var recentBookingAdapter: RecentBookingAdapter
    private var recentBookings: ArrayList<Bookings> = arrayListOf()

    override fun getViewModel(): BookingViewModel {
        mainViewModel = ViewModelProviders.of(this@BookingsActivity, appViewModelFactory)
            .get(BookingViewModel::class.java)
        return mainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_bookings)
        initToolbar()
        recentBookingAdapter =
            RecentBookingAdapter (
                this,
                recentBookings
            )
        rvBooking.apply {
            adapter = recentBookingAdapter
        }
        observeProgress()
        observeRestaurantList()
        mainViewModel.getBookings()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun observeRestaurantList() {
        mainViewModel.recentBookings.observe(this, Observer {
            recentBookings.addAll(it)
            recentBookingAdapter.notifyDataSetChanged()
        })
    }

    private fun observeProgress() {
        mainViewModel.loading.observe(this, Observer {
            setProgressVisibility(it!!)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else
            super.onOptionsItemSelected(item)
    }
    }
