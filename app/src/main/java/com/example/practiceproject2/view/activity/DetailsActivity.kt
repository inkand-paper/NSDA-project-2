package com.example.practiceproject2.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.practiceproject2.R
import com.example.practiceproject2.databinding.ActivityDetailsBinding
import com.example.practiceproject2.viewmodel.RoomViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: RoomViewModel
    private var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        userId = intent.getIntExtra("userId",-1)
        if (userId != -1){
            viewModel.getUserById(userId).observe(this){
                user ->
                user?.let {
                    binding.NameTV.text = "Name: ${it.name}"
                    binding.EmailTV.text = "Email: ${it.email}"
                    binding.DateTV.text = "Date: ${it.date}"
                    binding.PlaceTV.text = "Place: ${it.place}"
                    binding.NumberTV.text = "Number: ${it.number}"
                }
            }
        }

    }
}