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

package upv.dadm.ex23_sharedpreferences.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import upv.dadm.ex23_sharedpreferences.R
import upv.dadm.ex23_sharedpreferences.databinding.ActivityMainBinding

/**
 * Displays a help message to understand how to hide/show the initial welcome dialog.
 */
// The Hilt annotation @AndroidEntryPoint is required to receive dependencies from its parent class
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)
        // Make the custom ToolBar the ActionBar
        setSupportActionBar(binding.toolbar)
        // Get an instance of the NavController.
        // findNavController() does not work properly with FragmentContainerView in onCreate()
        val navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        // Configure the ActionBar to work with the NavController,
        // so that its title is updated when navigating
        // The AppBarConfiguration sets as starting fragments those included in the provided list.
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(setOf(R.id.mainFragment, R.id.welcomeDialogFragment))
        )
    }
}