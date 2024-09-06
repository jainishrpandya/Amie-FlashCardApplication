package com.example.newroomapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomapplication.databinding.ShowCardLayoutBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CardAdapter(val context: Context, val cardItems: LiveData<List<CardItem>>, val folderId: Int): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var cardItem: List<CardItem> = emptyList()




    init {
        cardItems.observeForever{
            newList ->
            setData(newList)
        }
    }

    private fun setData(newList: List<CardItem>){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var cardList = newList.filter { LocalDateTime.parse(it?.nextReview.toString(), formatter) < LocalDateTime.now() }
        this.cardItem = cardList.filter { it.folderId == folderId }
        notifyDataSetChanged()
    }



    inner class CardViewHolder(val binding: ShowCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ShowCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener{
            if (binding.questionCardText.visibility != View.GONE){
                binding.questionCardText.visibility = View.GONE
                binding.answerCardText.visibility = View.VISIBLE
                binding.belowText.text = "Click the card for Question"
            }else{
                binding.questionCardText.visibility = View.VISIBLE
                binding.answerCardText.visibility = View.GONE
                binding.belowText.text = "Click the card for Answer"
            }
        }

        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardItem.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.binding.questionCardText.text = cardItem[position].question
        holder.binding.answerCardText.text = cardItem[position].answer
    }

    // In your CardAdapter
    public fun getCurrentCardId(currentPosition: Int): Int {
        if (currentPosition in 0 until cardItem.size) {
            return cardItem[currentPosition].id // Assuming your CardItem has an 'id' property
        } else {
            return -1 // Or handle the case where the position is invalid
        }
    }
}