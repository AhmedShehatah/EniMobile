package com.ibrahim.enimobile.di

import com.ibrahim.enimobile.data.local.AppDatabase
import com.ibrahim.enimobile.data.remote.ApiService
import com.ibrahim.enimobile.data.repos.ClientRepo
import com.ibrahim.enimobile.data.repos.IClientRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideClientRepo(apiService: ApiService, db: AppDatabase): IClientRepo {
        return ClientRepo(apiService, db)
    }
}