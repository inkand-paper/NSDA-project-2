package com.example.practiceproject2.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.practiceproject2.R
import com.example.practiceproject2.databinding.ActivityAddInDetailsBinding
import com.example.practiceproject2.model.RoomEntity
import com.example.practiceproject2.viewmodel.RoomViewModel

class AddInDetails : AppCompatActivity() {
    private lateinit var binding: ActivityAddInDetailsBinding
    private lateinit var viewModel: RoomViewModel
    private var currentUserId: Int? = null
    private var currentUser: RoomEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        currentUserId = intent.getIntExtra("userId",-1)
        if (currentUserId != null && currentUserId != -1){
            viewModel.getUserById(currentUserId!!).observe(this){ user->
                if (user != null){
                    currentUser = user
                    binding.NameET.setText(user.name)
                    binding.EmailET.setText(user.email)
                    binding.DateET.setText(user.date)
                    binding.PlaceET.setText(user.place)
                    binding.NumberET.setText(user.number)
                    binding.SaveBtn.text = "Update"
                }
            }
        }
        else{
            binding.SaveBtn.text = "Save"
        }

        binding.SaveBtn.setOnClickListener {
            val name = binding.NameET.text.toString().trim()
            val email = binding.EmailET.text.toString().trim()
            val date = binding.DateET.text.toString().trim()
            val place = binding.PlaceET.text.toString().trim()
            val number = binding.NumberET.text.toString().trim()

            if (currentUser == null){
                viewModel.insert(RoomEntity(name = name, email = email, date = date, place = place, number = number))

            }else{
                val updatedUser = currentUser!!.copy(name = name, email = email, date = date, place = place, number = number)
                viewModel.update(updatedUser)
            }
            finish()
        }

    }
}