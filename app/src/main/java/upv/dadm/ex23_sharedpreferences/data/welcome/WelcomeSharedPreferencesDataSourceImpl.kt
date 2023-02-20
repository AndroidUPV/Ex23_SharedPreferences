/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex23_sharedpreferences.data.welcome

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Data source that provides access to SharedPreferences to get the user's preferences
 * about the visibility of the initial welcome dialog.
 * It implements the WelcomeSharedPreferencesDataSource interface.
 */
class WelcomeSharedPreferencesDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : WelcomeSharedPreferencesDataSource {

    // Constant defining the preferences keys
    private object PreferenceKeys {
        const val DIALOG_VISIBILITY =
            "upv.dadm.ex23_sharedpreferences.data.welcome.DIALOG_VISIBILITY"
    }

    /**
     * Returns the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun getInitialDialogVisibility() =
        // Operations must be moved to an IO optimised thread
        withContext(Dispatchers.IO) {
            return@withContext sharedPreferences.getBoolean(PreferenceKeys.DIALOG_VISIBILITY, true)
        }


    /**
     * Returns a Flow for the user's preference about the visibility of the initial welcome dialog.
     */
    override fun getDialogVisibility() = callbackFlow {
        // Define the listener that will be executed whenever a Preference changes its value
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch {
                if (PreferenceKeys.DIALOG_VISIBILITY == key) {
                    // Send the updated value for the desired Preference
                    trySend(getInitialDialogVisibility())
                }
            }
        }
        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        // Send an initial value
        trySend(getInitialDialogVisibility())
        // Unregister the listener when the channel is closed or cancelled
        awaitClose { sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) }
    }
        // Operations must be moved to an IO optimised thread
        .flowOn(Dispatchers.IO)

    /**
     * Sets the user's preference about the visibility of the initial welcome dialog.
     */
    override suspend fun setDialogVisibility(isVisible: Boolean) =
        // Operations must be moved to an IO optimised thread
        withContext(Dispatchers.IO) {
            sharedPreferences.edit {
                putBoolean(PreferenceKeys.DIALOG_VISIBILITY, isVisible)
            }
        }

}