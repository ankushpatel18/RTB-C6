package com.gihub.restaurant.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gihub.restaurant.di.key.ViewModelKey
import com.gihub.restaurant.ui.bookings.BookingViewModel
import com.gihub.restaurant.ui.gallery.GalleryViewModel
import com.gihub.restaurant.ui.restaurants.MainViewModel
import com.gihub.restaurant.ui.tables.TableViewModel
import com.gihub.restaurant.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module()
abstract class ViewModelBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TableViewModel::class)
    abstract fun bindTableViewModel(tableViewModel: TableViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    abstract fun bindGalleryViewModel(galleryViewModel: GalleryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    abstract fun bindBookingViewModel(bookingViewModel: BookingViewModel): ViewModel

}