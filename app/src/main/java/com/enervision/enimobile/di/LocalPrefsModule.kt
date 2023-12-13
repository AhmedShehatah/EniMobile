package com.enervision.enimobile.di

import android.content.Context
import com.enervision.enimobile.data.preferences.LocalPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalPrefsModule {

    @Singleton
    @Provides
    fun provideLocalPrefs(@ApplicationContext context: Context): LocalPref {
        return LocalPref(context)
    }
}