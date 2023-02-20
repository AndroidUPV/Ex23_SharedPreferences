/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex23_sharedpreferences.data.welcome

import kotlinx.coroutines.flow.Flow

/**
 * Interface declaring the methods that SharedPreferences exposes to Repositories.
 */
interface WelcomeSharedPreferencesDataSource {

    /**
     * Returns the user's preference about the visibility of the initial welcome dialog.
     */
    suspend fun getInitialDialogVisibility(): Boolean

    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    fun getDialogVisibility(): Flow<Boolean>

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    suspend fun setDialogVisibility(isVisible: Boolean)
}