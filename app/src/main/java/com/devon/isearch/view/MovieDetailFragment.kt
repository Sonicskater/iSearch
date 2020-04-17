package com.devon.isearch.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devon.isearch.R
import com.devon.isearch.databinding.FragmentMovieDetailBinding
import com.devon.isearch.viewmodel.ISearchModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    val view_model: ISearchModel by sharedViewModel()

    var index: Int? = null

    val args: MovieDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = args.index
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val temp = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val view = FragmentMovieDetailBinding.bind(temp)

        // configure view to display desired object
        index?.let {
            view_model.movies.value?.get(it)?.let { movie ->
                view.movieTitle.text = "${movie.title} (${movie.releaseYear})"
                view.movieDesc.text = movie.description
                view.movieGenre.text = movie.genre
                view.author.text = "Director: ${movie.artist}"
                if (movie.url.isNotBlank()) {
                    Picasso.get().load(movie.url).into(view.PrimaryImage)
                    Picasso.get().load(movie.url).transform(BlurTransformation(context, 15, 1)).into(view.BluuredImage)
                }
            }
        }

        view.imageButton.setOnClickListener {
            this.findNavController().navigateUp()
        }
        return view.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieDetailFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
