package com.gihub.restaurant.di.component

import android.app.Application
import com.gihub.restaurant.RestaurantApp
import com.gihub.restaurant.di.module.ActivityBindingModule
import com.gihub.restaurant.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class, ActivityBindingModule::class]
)
interface AppComponent: AndroidInjector<RestaurantApp> {

    @Component.Builder
     interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: RestaurantApp?)
}