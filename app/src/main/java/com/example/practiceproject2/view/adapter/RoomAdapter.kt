package com.example.practiceproject2.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceproject2.databinding.ItemListBinding
import com.example.practiceproject2.model.RoomEntity

class RoomAdapter(
    private val userList: MutableList<RoomEntity>,
    private val onItemClick: (RoomEntity) -> Unit,
    private val onEditClick: (RoomEntity) -> Unit,
    private val onDeleteClick: (RoomEntity) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    class RoomViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.apply {
            NameTV.text = "Name: ${user.name}"
            EmailTV.text = "Email: ${user.email}"
            DateTV.text = "Date: ${user.date}"
            PlaceTV.text = "Place: ${user.place}"
            NumberTV.text = "Number: ${user.number}"

            root.setOnClickListener {
                onItemClick(user)
            }

            EditBtn.setOnClickListener {
                onEditClick(user)
            }

            DeleteBtn.setOnClickListener {
                onDeleteClick(user)
            }
        }
    }

    override fun getItemCount(): Int = userList.size

    fun updateList(newList: List<RoomEntity>) {
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }
}