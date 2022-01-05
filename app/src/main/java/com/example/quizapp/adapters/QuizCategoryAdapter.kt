package com.example.quizapp.adapters

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizapp.QuizCategory
import com.example.quizapp.databinding.LayoutQuizCategoryItemBinding
import com.example.quizapp.ui.home.HomeFragment

class QuizCategoryAdapter(val activity: HomeFragment): RecyclerView.Adapter<QuizCategoryAdapter.ViewHolder>()
{
    class ViewHolder(binding: LayoutQuizCategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        val ivCategoryImage = binding.ivQuizCategoryImage
        val tvCategoryName = binding.tvCategoryName
    }

    private lateinit var quizCategoriesList: Array<QuizCategory>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val mBinding : LayoutQuizCategoryItemBinding = LayoutQuizCategoryItemBinding.inflate(activity.layoutInflater)

        return ViewHolder(mBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.tvCategoryName.text = quizCategoriesList[position].categoryName

        var imgurl = quizCategoriesList[position].imageLink

        if (imgurl.startsWith("http://"))
            imgurl = imgurl.replace("http://", "https://")


        Glide.with(activity.requireContext()).
        load(imgurl).centerCrop().
            into(holder.ivCategoryImage)

    }

    fun getData(quizCatList: Array<QuizCategory>)
    {
        quizCategoriesList = quizCatList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return quizCategoriesList.size
    }

}