package com.enervision.enimobile.di

import com.enervision.enimobile.data.local.AppDatabase
import com.enervision.enimobile.data.remote.ApiService
import com.enervision.enimobile.data.repos.ClientRepo
import com.enervision.enimobile.data.repos.IClientRepo
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