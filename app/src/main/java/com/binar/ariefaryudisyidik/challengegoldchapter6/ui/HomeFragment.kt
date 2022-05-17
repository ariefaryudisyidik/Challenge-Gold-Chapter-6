package com.binar.ariefaryudisyidik.challengegoldchapter6.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.ariefaryudisyidik.challengegoldchapter6.R
import com.binar.ariefaryudisyidik.challengegoldchapter6.data.remote.response.Movie
import com.binar.ariefaryudisyidik.challengegoldchapter6.databinding.FragmentHomeBinding
import com.binar.ariefaryudisyidik.challengegoldchapter6.helper.UserDataStoreManager
import com.binar.ariefaryudisyidik.challengegoldchapter6.ui.adapter.MovieAdapter
import com.binar.ariefaryudisyidik.challengegoldchapter6.viewmodel.MovieViewModel
import com.binar.ariefaryudisyidik.challengegoldchapter6.viewmodel.UserRepositoryViewModel
import com.binar.ariefaryudisyidik.challengegoldchapter6.viewmodel.UserViewModel
import com.binar.ariefaryudisyidik.challengegoldchapter6.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel by viewModels<MovieViewModel>()
    private val userRepositoryViewModel by viewModels<UserRepositoryViewModel>()
    private val args: HomeFragmentArgs by navArgs()

    //    private lateinit var userPreferences: UserPreferences
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: UserDataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        userPreferences = UserPreferences(requireContext())
//        binding.tvUsername.text = "Welcome, ${userPreferences.getLoggedInUser()}!"
        pref = UserDataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

//        viewModel.getUsername().observe(viewLifecycleOwner) {
//            binding.tvUsername.text = "Welcome, $it!"
//        }
        viewModel.getId().observe(viewLifecycleOwner) {
            if (it != 0) {
                val user = userRepositoryViewModel.getUser(it)
                binding.tvUsername.text = "Welcome, ${user.username}!"
            }
        }

        movieViewModel.movie.observe(viewLifecycleOwner) { setMovieData(it) }
        movieViewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
        binding.ibProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMovieData(movie: List<Movie>) {
        binding.apply {
            val movieAdapter = MovieAdapter(movie)
            rvMovies.setHasFixedSize(true)
            rvMovies.layoutManager = LinearLayoutManager(activity)
            rvMovies.adapter = movieAdapter
            movieAdapter.setOnItemClickCallback(object :
                MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                            data
                        )
                    )
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}