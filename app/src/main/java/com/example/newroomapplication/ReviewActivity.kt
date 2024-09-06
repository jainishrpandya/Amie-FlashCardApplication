package com.example.newroomapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newroomapplication.databinding.ActivityReviewBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReviewActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    private lateinit var showCardItems: List<CardItem>

    private lateinit var manager: CardStackLayoutManager
    private lateinit var cardAdapter: CardAdapter

    private val cardViewModel: CardViewModel by viewModels {
        CardItemModelFactory((application as TodoApplication).cardrepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.WHITE

        var folderId = intent.getIntExtra("folderId", -1)

        val adapter = CardAdapter(this, cardViewModel.cardItems, folderId)
        binding.cardStackView.adapter = adapter
        val layoutManager = binding.cardStackView.layoutManager as CardStackLayoutManager


        init()


        setCardView(folderId)

        binding.backButton.setOnClickListener{
            finish()
        }


        binding.RepeatCardButton.setOnClickListener{
            val currentPosition = layoutManager.topPosition
            val currentCardId = adapter.getCurrentCardId(currentPosition)
            cardViewModel.cardItems.value?.find { it.id == currentCardId }?.let {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val formatted = current.format(formatter)
                it!!.nextReview = formatted
                cardViewModel.updateCardItems(it)
            }
            binding.cardStackView.swipe()
        }

        binding.RepeatTodayButton.setOnClickListener{
            val currentPosition = layoutManager.topPosition
            val currentCardId = adapter.getCurrentCardId(currentPosition)
            cardViewModel.cardItems.value?.find { it.id == currentCardId }?.let {
                val current = LocalDateTime.now()
                val oneHourLater = current.plusHours(1)
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val formatted = oneHourLater.format(formatter)
                it!!.nextReview = formatted
                cardViewModel.updateCardItems(it)
            }
            binding.cardStackView.swipe()
        }

        binding.RepeatTomorrowButton.setOnClickListener{
            val currentPosition = layoutManager.topPosition
            val currentCardId = adapter.getCurrentCardId(currentPosition)
            cardViewModel.cardItems.value?.find { it.id == currentCardId }?.let {
                val current = LocalDateTime.now()
                val oneHourLater = current.plusDays(1)
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val formatted = oneHourLater.format(formatter)
                it!!.nextReview = formatted
                cardViewModel.updateCardItems(it)
            }
            binding.cardStackView.swipe()
        }
        binding.RepeatAfterIntervalButton.setOnClickListener{
            val currentPosition = layoutManager.topPosition
            val currentCardId = adapter.getCurrentCardId(currentPosition)
            cardViewModel.cardItems.value?.find { it.id == currentCardId }?.let {
                val current = LocalDateTime.now()
                val oneHourLater = current.plusDays(3)
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val formatted = oneHourLater.format(formatter)
                it!!.nextReview = formatted
                cardViewModel.updateCardItems(it)
            }
            binding.cardStackView.swipe()
        }

    }

    private fun init(){
        manager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {

            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }
        })
        manager.setVisibleCount(3)
        manager.setStackFrom(StackFrom.Top)
        manager.setSwipeableMethod(SwipeableMethod.Automatic)
        binding.cardStackView.layoutManager = manager
    }

    private fun setCardView(folderId: Int){
        val ReviewActivity = this
        val adapter = CardAdapter(ReviewActivity, cardViewModel.cardItems, folderId)
        binding.cardStackView.adapter = adapter
        }
}