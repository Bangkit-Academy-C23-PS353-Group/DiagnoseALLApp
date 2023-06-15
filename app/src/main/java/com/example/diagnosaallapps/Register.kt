package com.example.diagnosaallapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.diagnosaallapps.Api.ApiConfig
import com.example.diagnosaallapps.Response.ResponseRegister
import com.example.diagnosaallapps.databinding.ActivityLoginBinding
import com.example.diagnosaallapps.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            val username = binding.editText2.text.toString()
            val email = binding.edEmail.text.toString()
            val password = binding.editText.text.toString()
            val apiService = ApiConfig.getApiService()
            val register = apiService.ForRegister(username,email,password)
            register.enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null ) {
                            Toast.makeText(this@Register,"${responseBody.message}", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Register, Login::class.java)
                            startActivity(intent)
                        }

                    } else {
                        Toast.makeText(this@Register, "gagal register", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    Toast.makeText(this@Register, "gagal register", Toast.LENGTH_SHORT).show()
                }

            })

        }

    }
}