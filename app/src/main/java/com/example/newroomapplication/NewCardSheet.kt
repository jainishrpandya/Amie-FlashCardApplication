package com.example.newroomapplication

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newroomapplication.databinding.FragmentNewCardSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewCardSheet(var cardItem: CardItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewCardSheetBinding
    private lateinit var cardViewModel: CardViewModel
    private var folderId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()


        if (cardItem != null){
            binding.pageTitleTextView.text = "Edit Card"
            val editable = Editable.Factory.getInstance()
            binding.CardQuestionText.text = editable.newEditable(cardItem!!.question)
            binding.CardAnswerText.text = editable.newEditable(cardItem!!.answer)
            binding.deleteButton.visibility = View.VISIBLE
        } else {
            binding.pageTitleTextView.text = "New Card"
        }

        cardViewModel = ViewModelProvider(activity,CardItemModelFactory((activity.application as TodoApplication).cardrepository)).get(CardViewModel::class.java)
        binding.TodoAddButton.setOnClickListener {
            saveAction()
        }
        binding.deleteButton.setOnClickListener{
            deleteTask()
        }



    }

    private fun deleteTask() {
        cardViewModel.deleteCardItems(cardItem!!)
        dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        folderId = arguments?.getInt("folderId")
        // Inflate the layout for this fragment
        binding = FragmentNewCardSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction(){
        val question = binding.CardQuestionText.text.toString()
        val answer = binding.CardAnswerText.text.toString()
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // Choose your desired format
        val formattedDateTime = currentDateTime.format(formatter)
        if (cardItem == null) {
            val newCard = CardItem(question, answer, folderId, formattedDateTime)
            cardViewModel.addCardItems(newCard)
        } else {
            cardItem!!.question = question
            cardItem!!.answer = answer
            cardViewModel.updateCardItems(cardItem!!)
        }
        binding.CardQuestionText.setText("")
        binding.CardAnswerText.setText("")
        dismiss()
    }
}