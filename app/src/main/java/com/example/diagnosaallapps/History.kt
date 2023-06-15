package com.example.diagnosaallapps

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diagnosaallapps.Api.ApiConfig
import com.example.diagnosaallapps.DataStore.MainViewModel
import com.example.diagnosaallapps.DataStore.UserPreferences
import com.example.diagnosaallapps.DataStore.ViewModelFactory
import com.example.diagnosaallapps.databinding.ActivityHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class History : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var rv_listuser: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rv_listuser = findViewById<RecyclerView>(R.id.rv_listpasien)

        val layoutManager = LinearLayoutManager(this)
        binding.rvListpasien.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListpasien.addItemDecoration(itemDecoration)
        getUser()

    }

    private fun getUser() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getuser().observe(this){
            val apiService = ApiConfig.getApiService()
            val uploadImage =
                apiService.ForHistory( "Bearer ${it.token}")
            uploadImage.enqueue(object : Callback<Array<Array<String>>> {
                override fun onResponse(
                    call: Call<Array<Array<String>>>,
                    response: Response<Array<Array<String>>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val datauser = responseBody
                        val adapter = datauser?.let { it1 -> adapterUser(it1) }
                        binding.rvListpasien.adapter = adapter

                    } else {

                    }
                }

                override fun onFailure(call: Call<Array<Array<String>>>, t: Throwable) {

                }

            })
        }
    }


}