/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex23_sharedpreferences.ui.welcome

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
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

    // Backing property for displaying the initial dialog (default empty until initialised)
    private val _showInitialDialog = MutableLiveData<Boolean>()

    // Whether to show or hide the initial dialog
    val showInitialDialog: LiveData<Boolean>
        get() = _showInitialDialog

    // Initialise the property for displaying the initial dialog
    init {
        // As it is a blocking operation it should be executed in a thread
        viewModelScope.launch {
            // Get the value from the repository
            _showInitialDialog.value =
                welcomeSharedPreferencesRepository.getInitialDialogVisibility()
        }
    }

    // Whether to show the display or hide option menu
    // according to the preference selected by the user for displaying the initial dialog
    val showDialogMenu =
        // Transform the received Flow into LiveData to update the UI in a lifecycle aware manner
        welcomeSharedPreferencesRepository.getDialogVisibility().asLiveData()

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