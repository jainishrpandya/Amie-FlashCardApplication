package com.example.newroomapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomapplication.databinding.CardItemCellBinding

class CardItemAdapter (
    private val cardItems: List<CardItem>,
    private val clickListener: CardItemClickListener
): RecyclerView.Adapter<CardItemViewHolder>() {

    private var filteredItems: MutableList<CardItem> = cardItems.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardItemCellBinding.inflate(from, parent, false)
        return CardItemViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        holder.bindCardItem(filteredItems[position])
    }

    override fun getItemCount(): Int = filteredItems.size

    fun filterByFolderId(folderId:Int) {
        filteredItems.clear()
        if (folderId == 0) {
            filteredItems.addAll(cardItems)
        } else {
            val filtered = cardItems.filter{ it.folderId == folderId } // Filter for matching folderId
            filteredItems.addAll(filtered)
        }
        notifyDataSetChanged()
    }

    }