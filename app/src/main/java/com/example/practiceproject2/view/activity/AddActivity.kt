package com.example.practiceproject2.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.practiceproject2.R
import com.example.practiceproject2.databinding.ActivityAddBinding
import com.example.practiceproject2.model.RoomEntity
import com.example.practiceproject2.view.adapter.RoomAdapter
import com.example.practiceproject2.viewmodel.RoomViewModel

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var adapter: RoomAdapter
    private val userList = mutableListOf<RoomEntity>()
    private lateinit var viewModel: RoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        adapter = RoomAdapter(userList, onItemClick = {
            user ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("userId",user.id)
            startActivity(intent)
        }, onEditClick = {user ->
            val intent = Intent(this, AddInDetails::class.java)
            intent.putExtra("userId",user.id)
            startActivity(intent)
        }, onDeleteClick = {
            user ->
            viewModel.delete(user)
        })
        binding.RecyclerView.adapter = adapter

        viewModel.getAllData().observe(this){users ->
        userList.clear()
        userList.addAll(users)
            adapter.notifyDataSetChanged()
        }
        binding.AddBtn.setOnClickListener {
            val intent = Intent(this, AddInDetails::class.java)
            startActivity(intent)
        }
    }
}