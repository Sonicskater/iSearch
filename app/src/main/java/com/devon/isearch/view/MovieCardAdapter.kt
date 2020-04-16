package com.devon.isearch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.devon.isearch.R
import com.devon.isearch.viewmodel.ISearchModel

class MovieCardAdapter(val viewModel: ISearchModel) : RecyclerView.Adapter<MovieCardAdapter.MovieViewHolder>() {

    class MovieViewHolder(val textView: ConstraintLayout): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.test_text_view, parent, false) as ConstraintLayout

        return MovieViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return viewModel.movies.value!!.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }
}