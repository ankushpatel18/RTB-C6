package com.gihub.restaurant.ui.restaurants

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.Restaurant
import com.gihub.restaurant.ui.base.BaseActivity
import com.gihub.restaurant.ui.bookings.BookingsActivity
import com.gihub.restaurant.ui.tables.TablesActivity
import com.gihub.restaurant.utils.Constants
import com.gihub.restaurant.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_restaurants.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), RestaurantListAdapter.OnItemClicked {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    private lateinit var restaurantListAdapter: RestaurantListAdapter
    private var restaurants: ArrayList<Restaurant> = arrayListOf()

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProviders.of(this@MainActivity, appViewModelFactory)
            .get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        restaurantListAdapter =
            RestaurantListAdapter(
                this,
                restaurants
            )
        restaurantListAdapter.setOnRowClicked(this)
        rvRestaurants.apply {
            adapter = restaurantListAdapter
        }
        observeProgress()
        observeRestaurantList()
        mainViewModel.getRestaurants()

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.showBookings -> {
                    startActivity(Intent(this, BookingsActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun observeRestaurantList() {
        mainViewModel.restaurants.observe(this, Observer {
            restaurants.addAll(it)
            restaurantListAdapter.notifyDataSetChanged()
        })
    }

    private fun observeProgress() {
        mainViewModel.loading.observe(this, Observer {
            setProgressVisibility(it!!)
        })
    }

    override fun onRestaurantClicked(restaurant: Restaurant) {
        val intent = Intent(this, TablesActivity::class.java)
        intent.putExtra(Constants.ParamsConst.RESTAURANT_ID, restaurant.id)
        intent.putExtra(Constants.ParamsConst.RESTAURANT_NAME, restaurant.name)
        startActivity(intent)
    }
}
