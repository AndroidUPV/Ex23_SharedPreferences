/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex23_sharedpreferences.data.welcome

import kotlinx.coroutines.flow.Flow

/**
 * Interface declaring the methods that the Repository exposes to ViewModels.
 */
interface WelcomeSharedPreferencesRepository {
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