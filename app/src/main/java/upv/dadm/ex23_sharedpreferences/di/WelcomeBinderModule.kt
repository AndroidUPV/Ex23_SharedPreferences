/*
 * Copyright (c) 2022-2024 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex23_sharedpreferences.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import upv.dadm.ex23_sharedpreferences.data.welcome.WelcomeSharedPreferencesDataSource
import upv.dadm.ex23_sharedpreferences.data.welcome.WelcomeSharedPreferencesDataSourceImpl
import upv.dadm.ex23_sharedpreferences.data.welcome.WelcomeSharedPreferencesRepository
import upv.dadm.ex23_sharedpreferences.data.welcome.WelcomeSharedPreferencesRepositoryImpl
import javax.inject.Singleton

/**
 * Hilt module that determines how to provide required dependencies for interfaces.
 */
@Module
// The Hilt annotation @SingletonComponent creates and destroy instances following the lifecycle of the Application
@InstallIn(SingletonComponent::class)
abstract class WelcomeBinderModule {

    /**
     * Provides an instance of a WelcomeSharedPreferencesRepository.
     */
    @Binds
    @Singleton
    abstract fun bindWelcomePreferencesRepository(
        welcomePreferencesRepositoryImpl: WelcomeSharedPreferencesRepositoryImpl
    ): WelcomeSharedPreferencesRepository

    /**
     * Provides an instance of a WelcomeSharedPreferencesDataSource.
     */
    @Binds
    @Singleton
    abstract fun bindWelcomePreferencesDataSource(
        welcomeSharedPreferencesDataSourceImpl: WelcomeSharedPreferencesDataSourceImpl
    ): WelcomeSharedPreferencesDataSource
}