/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex23_sharedpreferences.data.welcome

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository for retrieving the user's preference about the visibility of the initial welcome dialog.
 * It implements the WelcomeSharedPreferencesRepository interface.
 */
// @Inject enables Hilt to provide the required dependencies
class WelcomeSharedPreferencesRepositoryImpl @Inject constructor(
    private val welcomeSharedPreferencesDataSource: WelcomeSharedPreferencesDataSource
) : WelcomeSharedPreferencesRepository {

    /**
     * Returns the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun getInitialDialogVisibility(): Boolean =
        welcomeSharedPreferencesDataSource.getInitialDialogVisibility()

    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    override fun getDialogVisibility(): Flow<Boolean> =
        welcomeSharedPreferencesDataSource.getDialogVisibility()

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun setDialogVisibility(isVisible: Boolean) {
        welcomeSharedPreferencesDataSource.setDialogVisibility(isVisible)
    }
}