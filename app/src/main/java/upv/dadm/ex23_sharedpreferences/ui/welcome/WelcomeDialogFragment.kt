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

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import upv.dadm.ex23_sharedpreferences.R
import upv.dadm.ex23_sharedpreferences.databinding.FragmentWelcomeBinding

/**
 * Display a custom dialog to welcome the user to the app.
 * A Switch is used to set the user preference about whether to
 * show or hide the dialog after the app starts.
 */
// The Hilt annotation @AndroidEntryPoint is required to receive dependencies from its parent class
@AndroidEntryPoint
class WelcomeDialogFragment : DialogFragment(R.layout.fragment_welcome) {

    // Reference to a ViewModel shared between Fragments
    private val viewModel: WelcomeViewModel by activityViewModels()

    // Backing property to resource binding
    private var _binding: FragmentWelcomeBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = FragmentWelcomeBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Set the preference to display the dialog according to the user's selection
        viewModel.setDialogVisibility(binding.swControlDialog.isChecked)
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }
}