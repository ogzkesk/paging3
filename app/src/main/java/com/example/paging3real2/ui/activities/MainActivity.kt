package com.example.paging3real2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paging3real2.R
import com.example.paging3real2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}