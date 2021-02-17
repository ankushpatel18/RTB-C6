package com.gihub.restaurant.di.module

import android.app.Application
import android.content.Context
import com.gihub.restaurant.data.AppDataManager
import com.gihub.restaurant.data.IAppDataSource
import com.gihub.restaurant.data.remote.IRemoteSource
import com.gihub.restaurant.data.remote.RemoteDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelBindingModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun getRemoteSource(remoteDataManager: RemoteDataManager): IRemoteSource = remoteDataManager

    @Provides
    @Singleton
    internal fun provideDataManger(appDataManager: AppDataManager): IAppDataSource = appDataManager
}