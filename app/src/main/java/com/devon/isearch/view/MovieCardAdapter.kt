package com.devon.isearch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.devon.isearch.R
import com.devon.isearch.databinding.TestTextViewBinding
import com.devon.isearch.viewmodel.ISearchModel

class MovieCardAdapter(val viewModel: ISearchModel) : RecyclerView.Adapter<MovieCardAdapter.MovieViewHolder>() {

    class MovieViewHolder(val view: CardView): RecyclerView.ViewHolder(view){
        val binding = TestTextViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.test_text_view, parent, false) as CardView
        textView.setOnClickListener {
            val pos = (parent as RecyclerView).getChildAdapterPosition(it)
            val item = viewModel.movies.value?.get(pos)
            Toast.makeText(parent.context, item?.title?:"None", Toast.LENGTH_SHORT).show()
        }
        return MovieViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return viewModel.movies.value!!.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.itemTextView.text = viewModel.movies.value?.get(position)?.title ?: "Title not found"
    }


}