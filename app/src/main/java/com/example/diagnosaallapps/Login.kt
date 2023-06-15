package com.example.diagnosaallapps

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.diagnosaallapps.Api.ApiConfig
import com.example.diagnosaallapps.DataStore.MainViewModel
import com.example.diagnosaallapps.DataStore.UserPreferences
import com.example.diagnosaallapps.DataStore.UserViewModel
import com.example.diagnosaallapps.DataStore.ViewModelFactory
import com.example.diagnosaallapps.Response.ResponseLogin
import com.example.diagnosaallapps.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[MainViewModel::class.java]
        binding.button.setOnClickListener{
            val email = binding.edEmail.text.toString()
            val password = binding.editText.text.toString()
            val apiService = ApiConfig.getApiService()
            val login = apiService.Forlogin(email,password)
            login.enqueue(object : Callback<ResponseLogin>{
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null ) {
                            val UserData = UserViewModel(responseBody.accessToken.toString())
                            mainViewModel.saveuser(UserData)
                            Toast.makeText(this@Login, "${responseBody.message}", Toast.LENGTH_SHORT).show()
//                            val intent = Intent(this@Login, HomeActivity::class.java)
//                            startActivity(intent)
                            if(responseBody.message=="Login Success"){
                                val intent = Intent(this@Login, MainActivity::class.java)
                                startActivity(intent)
                            }

                        }

                    } else {
                        Toast.makeText(this@Login, "Email dan Password kurang tepat", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Toast.makeText(this@Login, "Email dan Password kurang tepat", Toast.LENGTH_SHORT).show()
                }

            })

        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }


    }


}