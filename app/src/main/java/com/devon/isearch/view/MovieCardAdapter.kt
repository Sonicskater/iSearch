package com.devon.isearch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.devon.isearch.R
import com.devon.isearch.databinding.TestTextViewBinding
import com.devon.isearch.viewmodel.ISearchModel
import com.squareup.picasso.Picasso

class MovieCardAdapter(val viewModel: ISearchModel, val nav: () -> NavController) : RecyclerView.Adapter<MovieCardAdapter.MovieViewHolder>() {

    class MovieViewHolder(val view: CardView): RecyclerView.ViewHolder(view){
        val binding = TestTextViewBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.test_text_view, parent, false) as CardView
        textView.setOnClickListener {
            val pos = (parent as RecyclerView).getChildAdapterPosition(it)

            // connectivity message
            if (!viewModel.connected()){
                val x = Toast.makeText(parent.context, "No internet connection, only cached results will be available", Toast.LENGTH_SHORT)
                x.show()
            }
            nav().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(pos))
        }
        return MovieViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return viewModel.movies.value!!.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        viewModel.movies.value?.get(position)?.let {
            holder.binding.itemTextView.text = it.title
            if (it.url.isNotBlank()) {
                Picasso.get().load(it.url).into(holder.binding.moviePoster)
            }
        }
    }


}