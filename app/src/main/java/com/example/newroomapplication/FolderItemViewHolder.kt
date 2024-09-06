package com.example.newroomapplication

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomapplication.databinding.FolderItemCellBinding

class FolderItemViewHolder(
    private val context: Context,
    private val binding: FolderItemCellBinding,
    private val clickListener: FolderItemClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bindTaskItem(taskItem: TaskItem){
//
        binding.name.text = taskItem.folderName

        binding.taskCellContainer.setOnClickListener{
            val intent = Intent(context, CardListActivity::class.java)
            intent.putExtra("folderName",taskItem.folderName)
            intent.putExtra("folderId", taskItem.id)
            context.startActivity(intent)
        }
        binding.EditButton.setOnClickListener{
            clickListener.editFolderItems(taskItem)
        }



    }
}