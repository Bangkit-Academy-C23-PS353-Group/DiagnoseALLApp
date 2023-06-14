package com.example.diagnosaallapps

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.diagnosaallapps.DataStore.MainViewModel
import com.example.diagnosaallapps.DataStore.UserPreferences
import com.example.diagnosaallapps.DataStore.ViewModelFactory
import com.example.diagnosaallapps.databinding.ActivityMainBinding
import com.example.diagnosaallapps.databinding.ActivityProfileBinding
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class Profile : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[MainViewModel::class.java]


        binding.btnEditUserProfile.setOnClickListener{
            val intent = Intent(this, Upload::class.java)
            startActivity(intent)
        }
        binding.btnlogout.setOnClickListener{
            mainViewModel.logout()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}