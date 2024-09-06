package com.example.newroomapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomapplication.databinding.FolderItemCellBinding



class FolderItemAdapter(
    private val taskItems: List<TaskItem>,
    private val clickListener: FolderItemClickListener
): RecyclerView.Adapter<FolderItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FolderItemCellBinding.inflate(from, parent, false)
        return FolderItemViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = taskItems.size

    override fun onBindViewHolder(holder: FolderItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }

}