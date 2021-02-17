package com.gihub.restaurant.di.module

import com.gihub.restaurant.di.scope.ActivityScoped
import com.gihub.restaurant.ui.bookings.BookingsActivity
import com.gihub.restaurant.ui.gallery.GalleryActivity
import com.gihub.restaurant.ui.restaurants.MainActivity
import com.gihub.restaurant.ui.tables.TablesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    @ActivityScoped
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector()
    @ActivityScoped
    abstract fun tableActivity(): TablesActivity


    @ContributesAndroidInjector()
    @ActivityScoped
    abstract fun galleryActivity(): GalleryActivity

    @ContributesAndroidInjector()
    @ActivityScoped
    abstract fun bookingActivity(): BookingsActivity
}