package com.example.newroomapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newroomapplication.databinding.ActivityCardListBinding

class CardListActivity : AppCompatActivity(), CardItemClickListener {
    private lateinit var binding: ActivityCardListBinding

    private val cardViewModel: CardViewModel by viewModels {
        CardItemModelFactory((application as TodoApplication).cardrepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE

        var folderName = intent.getStringExtra("folderName") ?: ""
        var folderId = intent.getIntExtra("folderId", -1)

        binding.FolderName.text = folderName

        binding.newCardButton.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("folderId", folderId)
            val BottomSheet = NewCardSheet(null)
            BottomSheet.arguments = bundle
            BottomSheet.show(supportFragmentManager, "newTaskTag")
        }

        binding.reviewCardsButton.setOnClickListener{
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("folderId", folderId)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener{
            finish()
        }

        setRecyclerView(folderId)
    }


    private fun setRecyclerView(folderId: Int){
        val CardListActivity = this
        cardViewModel.cardItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = CardItemAdapter(it, CardListActivity)
                val adapter = this.adapter as CardItemAdapter
                adapter.filterByFolderId(folderId)
                
            }
        }
    }

    override fun deleteCardItems(cardItem: CardItem) {
        cardViewModel.deleteCardItems(cardItem)
    }

    override fun editCardItems(cardItem: CardItem) {
        NewCardSheet(cardItem).show(supportFragmentManager, "newTaskTag")
    }
}