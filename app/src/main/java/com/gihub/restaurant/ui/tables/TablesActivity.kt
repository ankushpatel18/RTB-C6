package com.gihub.restaurant.ui.tables

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.gihub.restaurant.R
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.ui.base.BaseActivity
import com.gihub.restaurant.ui.gallery.GalleryActivity
import com.gihub.restaurant.utils.Constants
import com.gihub.restaurant.viewmodel.AppViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_table_selection.*
import javax.inject.Inject


class TablesActivity : BaseActivity<TableViewModel>(), TableListAdapter.OnItemClicked {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    private lateinit var mainViewModel: TableViewModel

    private lateinit var tableListAdapter: TableListAdapter
    private var tables: ArrayList<DinePoint> = arrayListOf()
    private var selectedRestaurantId: Int = 0
    private var selectedRestaurantName: String = ""

    override fun getViewModel(): TableViewModel {
        mainViewModel = ViewModelProviders.of(this@TablesActivity, appViewModelFactory)
            .get(TableViewModel::class.java)
        return mainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedRestaurantId = intent.getIntExtra(Constants.ParamsConst.RESTAURANT_ID, 0)
        selectedRestaurantName = intent.getStringExtra(Constants.ParamsConst.RESTAURANT_NAME)
        setContentView(R.layout.activity_table_selection)
        initToolbar()
        tableListAdapter =
            TableListAdapter(
                this,
                tables
            )
        tableListAdapter.setOnRowClicked(this)
        rvTables.apply {
            adapter = tableListAdapter
            layoutManager = GridLayoutManager(this@TablesActivity, 2)
        }
        observeProgress()
        observeTableListResponse()
        mainViewModel.getTables(selectedRestaurantId)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.title = selectedRestaurantName
    }

    private fun observeTableListResponse() {
        mainViewModel.tables.observe(this, Observer {
            tables.addAll(it)
            tableListAdapter.notifyDataSetChanged()
        })
    }

    private fun observeProgress() {
        mainViewModel.loading.observe(this, Observer {
            setProgressVisibility(it!!)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else
            super.onOptionsItemSelected(item)
    }

    override fun onTableSelected(selectedTable: DinePoint) {
        val intent = Intent(this, GalleryActivity::class.java)
        intent.putExtra(Constants.ParamsConst.RESTAURANT_ID, selectedRestaurantId)
        intent.putExtra(Constants.ParamsConst.TABLE_ID, selectedTable.tableId)
        startActivity(intent)
//        tables.forEachIndexed { index, table ->
//            tables[index].isSelected = (table.tableId == selectedTable.tableId)
//        }
    }

//    private fun getSelectedTable() : DinePoint? {
//        for(table in tables) {
//            if (table.isSelected) {
//                return table;
//            }
//        }
//        return null
//    }
}
