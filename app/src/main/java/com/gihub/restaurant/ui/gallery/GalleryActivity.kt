package com.gihub.restaurant.ui.gallery

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gihub.restaurant.R
import com.gihub.restaurant.ui.base.BaseActivity
import com.gihub.restaurant.ui.restaurants.MainActivity
import com.gihub.restaurant.utils.Constants
import com.gihub.restaurant.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_table_gallery.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList

class GalleryActivity : BaseActivity<GalleryViewModel>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    private lateinit var galleryViewModal: GalleryViewModel

    private lateinit var galleryAdapter: GalleryListAdapter
    private var tables: ArrayList<String> = arrayListOf()
    private var selectedRestaurantId: Int = 0
    private var selectedTableId: Int = 0


    var selectedCalendar = Calendar.getInstance()


    override fun getViewModel(): GalleryViewModel {
        galleryViewModal = ViewModelProviders.of(this@GalleryActivity, appViewModelFactory)
            .get(GalleryViewModel::class.java)
        return galleryViewModal
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_gallery)
        selectedRestaurantId = intent.getIntExtra(Constants.ParamsConst.RESTAURANT_ID, 0)
        selectedTableId = intent.getIntExtra(Constants.ParamsConst.TABLE_ID, 0)
        initToolbar()
        updateDateTimeUi()
        galleryAdapter =
            GalleryListAdapter(
                this,
                tables
            )
        rvPhotos.apply {
            adapter = galleryAdapter
        }
        observeProgress()
        observeBookingStatus()
        observeTableListResponse()
        galleryViewModal.getPictures(selectedRestaurantId, selectedTableId)
        tvDate.setOnClickListener {
            showDatePicker()
        }
        tvTime.setOnClickListener {
            showTimePicker()
        }

        btnBookNow.setOnClickListener {
            galleryViewModal.bookTable(selectedRestaurantId, selectedTableId, selectedCalendar.timeInMillis)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun observeTableListResponse() {
        galleryViewModal.dinepointGallery.observe(this, Observer {
            tables.addAll(it)
            galleryAdapter.notifyDataSetChanged()
        })
    }

    private fun observeProgress() {
        galleryViewModal.loading.observe(this, Observer {
            setProgressVisibility(it!!)
        })
    }

    private fun observeBookingStatus() {
        galleryViewModal.bookingDone.observe(this, Observer {
            if (it) {
                showInfoToast("Table booked successfully")
                val intent= Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            else {
                showInfoToast("Failed to book table.")
            }
        })
    }

    private fun showDatePicker() {
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            selectedCalendar.set(Calendar.YEAR, year)
            selectedCalendar.set(Calendar.MONTH, monthOfYear)
            selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        }, selectedCalendar.get(Calendar.YEAR), selectedCalendar.get(Calendar.MONTH), selectedCalendar.get(Calendar.DAY_OF_MONTH))
        dpd.datePicker.minDate = selectedCalendar.timeInMillis
        dpd.show()
    }

    private fun showTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            selectedCalendar.set(Calendar.HOUR_OF_DAY, hour)
            selectedCalendar.set(Calendar.MINUTE, minute)
            updateDateTimeUi()
        }
        TimePickerDialog(this, timeSetListener, selectedCalendar.get(Calendar.HOUR_OF_DAY), selectedCalendar.get(Calendar.MINUTE), true).show()
    }

    private fun updateDateTimeUi() {
        tvTime.text = SimpleDateFormat("hh:mm a").format(selectedCalendar.time)
        tvDate.text = SimpleDateFormat("dd/MMM/yyyy").format(selectedCalendar.time)
    }
}
