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
import DiagnoseAllApp
import androidx.activity.compose.setContent
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[MainViewModel::class.java]


        mainViewModel.getuser().observe(this){
            if (it.token ==""){
                val intent = Intent(this,Login::class.java)
                startActivity(intent)
            }
            else{
              //setContentView(R.layout.activity_upload)
                setContent {
                 DiagnoseAllApp()
                }
//                 val intent = Intent(this,Profile::class.java)
//                 startActivity(intent)
            }
        }


    }
}