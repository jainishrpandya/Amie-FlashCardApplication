package com.example.newroomapplication

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomapplication.databinding.CardItemCellBinding

class CardItemViewHolder (
    private val context: Context,
    private val binding: CardItemCellBinding,
    private val clickListener: CardItemClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bindCardItem(cardItem: CardItem){

        binding.name.text = cardItem.question
        binding.nextReview.text = "Next Review : " + cardItem.nextReview

        binding.taskCellContainer.setOnClickListener{
            clickListener.editCardItems(cardItem)
        }
    }
}