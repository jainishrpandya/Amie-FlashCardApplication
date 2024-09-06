package com.example.newroomapplication

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newroomapplication.databinding.FragmentNewFolderSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime


class NewFolderSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewFolderSheetBinding
    private lateinit var folderViewModel: FolderViewModel
    private var dueTime: LocalTime? = null
    private var selectedPriority = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        // CHECK NEW TASK OR EDIT TASK
        if (taskItem != null) {
            binding.pageTitleTextView.text = "Edit Folder Name"
            val editable = Editable.Factory.getInstance()
            binding.TodoTitleText.text = editable.newEditable(taskItem!!.folderName)
            binding.deleteButton.visibility = View.VISIBLE
        } else {
            binding.pageTitleTextView.text = "New Folder"
        }

        // TASK ACTIONS
        folderViewModel = ViewModelProvider(activity).get(FolderViewModel::class.java)
        binding.TodoAddButton.setOnClickListener {
            saveAction()
        }
        binding.deleteButton.setOnClickListener {
            deleteTaskItem()
        }

    }

    private fun deleteTaskItem() {
        folderViewModel.deleteTaskItems(taskItem!!)
        dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentNewFolderSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction(){
        val folderName = binding.TodoTitleText.text.toString()
        if (taskItem == null){
            val newTask = TaskItem(folderName)
            folderViewModel.addTaskItems(newTask)
        } else {
            taskItem!!.folderName = folderName
            folderViewModel.updateTaskItems(taskItem!!)
        }
        binding.TodoTitleText.setText("")
        dismiss()
    }



}