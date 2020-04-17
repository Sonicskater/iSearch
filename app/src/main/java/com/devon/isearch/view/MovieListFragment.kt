package com.devon.isearch.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView

import com.devon.isearch.databinding.FragmentMovieListBinding
import com.devon.isearch.viewmodel.ISearchModel
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    val view_model: ISearchModel by sharedViewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    // view binding stuff
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    // will intentionally throw null pointer error if accessed at an invalid point in lifecycle

    lateinit var connectionToast: Toast

    @SuppressLint("ShowToast") // toast is shown later in class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectionToast = Toast.makeText(context, "No internet connection, only cached results will be available", Toast.LENGTH_SHORT)
        view_model.movies.observe(this, Observer {
            binding.movieList.adapter?.notifyDataSetChanged()
        })
    }

    val nav get() =  this.findNavController()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        val view = binding.root
        viewManager = GridLayoutManager(context,2)

        // nav is provided as a lambda because of issues encountered with testing android.nav.
        // Fixes it because this "lazily" handles the problem, fixing the
        // test as in a test the navController must be set AFTER the fragment is inflated (therefore after this method is called)
        viewAdapter = MovieCardAdapter(view_model) {nav}

        // set up search bar listener
        view.search_bar.addTextChangedListener {
            view_model.searchString = it.toString()
            view.movie_list.adapter?.notifyDataSetChanged()

            // connectivity alert
            if (!view_model.connected()){
                connectionToast.setGravity(Gravity.CENTER, 0,0)
                if (connectionToast.view.windowVisibility != View.VISIBLE){
                    connectionToast.show()
                }
            }
        }

        // initialize recycler view
        view.movie_list.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }

        // refresh gesture listener
        view.movie_refresh.setOnRefreshListener {
            view_model.searchString = view_model.searchString
            view.movie_list.adapter?.notifyDataSetChanged()
            view.movie_refresh.isRefreshing = false
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieListFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
