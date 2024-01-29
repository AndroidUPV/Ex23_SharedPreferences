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

package upv.dadm.ex23_sharedpreferences.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import upv.dadm.ex23_sharedpreferences.data.welcome.WelcomeSharedPreferencesRepository
import javax.inject.Inject

/**
 * Holds information about the preference of the user to show or hide the initial welcome dialog.
 */
// The Hilt annotation @HiltEntryPoint is required to receive dependencies from its parent class
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val welcomeSharedPreferencesRepository: WelcomeSharedPreferencesRepository
) : ViewModel() {

    // Whether to show or hide the initial dialog
    val showInitialDialog = flow {
        val initialDialogVisibility =
            welcomeSharedPreferencesRepository.getInitialDialogVisibility()
        emit(initialDialogVisibility)
    }

    // Whether to show the display or hide option menu
    // according to the preference selected by the user for displaying the initial dialog
    val showDialogMenu =
        // Transform the received Flow into StateFlow to update the UI in a lifecycle aware manner
        welcomeSharedPreferencesRepository.getDialogVisibility().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = true
        )

    /**
     * Sets the preference to display the initial dialog according to the user selection.
     */
    fun setDialogVisibility(isVisible: Boolean) {
        // As it is a blocking operation it should be executed in a thread
        viewModelScope.launch {
            welcomeSharedPreferencesRepository.setDialogVisibility(isVisible)
        }
    }


}