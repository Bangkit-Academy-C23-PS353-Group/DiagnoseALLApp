package com.example.diagnosaallapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diagnosaallapps.databinding.ActivityResultBinding
import com.example.diagnosaallapps.databinding.ActivityUploadBinding

class Result : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val patientname = intent.getStringExtra("patientname").toString()
        val result = intent.getStringExtra("result").toString()
        val fotodarah = intent.getStringExtra("imageDiagnose")?.toInt()
        binding.textView4.text= patientname
        binding.textView6.text = result
        if (fotodarah != null) {
            binding.imageView4.setImageResource(fotodarah)
        }

    }
}