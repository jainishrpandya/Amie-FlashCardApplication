package com.example.newroomapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newroomapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FolderItemClickListener {
    private lateinit var binding : ActivityMainBinding

    private val taskViewModel: FolderViewModel by viewModels {
        FolderItemModelFactory((application as TodoApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //top status bar
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true // true or false
        window.statusBarColor = Color.WHITE

        setAppTheme(false)

        binding.FolderAddButton.setOnClickListener(){
            NewFolderSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val MainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = FolderItemAdapter(it, MainActivity)
            }
        }
    }

    override fun editFolderItems(taskItem: TaskItem) {
        NewFolderSheet(taskItem).show(supportFragmentManager, "newTaskTag")
    }

    override fun deleteFolderItems(taskItem: TaskItem) {
        taskViewModel.deleteTaskItems(taskItem)
    }

    private fun setAppTheme(isDarkMode: Boolean){
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}