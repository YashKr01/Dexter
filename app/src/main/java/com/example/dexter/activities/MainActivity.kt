package com.example.dexter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dexter.databinding.ActivityMainBinding
import com.example.dexter.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

    }
}