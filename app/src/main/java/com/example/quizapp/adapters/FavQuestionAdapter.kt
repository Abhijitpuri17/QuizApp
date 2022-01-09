package com.example.quizapp.adapters

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.QuestionLikedItemBinding
import com.example.quizapp.models.Question
import com.example.quizapp.models.entities.FavQuestion
import com.example.quizapp.views.fragments.FavouritesFragment

class FavQuestionAdapter(val fragment : Fragment) : RecyclerView.Adapter<FavQuestionAdapter.ViewHolderQuestionLiked>()
{

    var questionList = ArrayList<FavQuestion>()

   class ViewHolderQuestionLiked(binding: QuestionLikedItemBinding) : RecyclerView.ViewHolder(binding.root)
   {
       val queText = binding.tvQuestion
       val ansText = binding.tvAnswer
       val topic = binding.tvTopic
       val deleteBtn = binding.ibDeleteQuestion
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuestionLiked {

        val binding_ = QuestionLikedItemBinding.inflate(fragment.layoutInflater)




        return ViewHolderQuestionLiked(binding_)
    }

    override fun onBindViewHolder(holder: ViewHolderQuestionLiked, position: Int)
    {
        val que = questionList[position]

        holder.queText.text = que.queText

        holder.ansText.text = que.ansText

        holder.topic.text = que.topic

        holder.deleteBtn.setOnClickListener {
            if (fragment is FavouritesFragment) {
                fragment.deleteQue(que)
            }
        }


    }

    fun getData(questionList: ArrayList<FavQuestion>)
    {
        this.questionList = questionList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}