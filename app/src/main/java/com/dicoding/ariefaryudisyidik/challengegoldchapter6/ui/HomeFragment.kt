package com.dicoding.ariefaryudisyidik.challengegoldchapter6.ui

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
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.R
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.adapter.MovieAdapter
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local.UserRepository
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.remote.response.Movie
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.databinding.FragmentHomeBinding
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.MainViewModel
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.UserDataStoreManager
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.ViewModelFactory
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.viewmodel.MovieViewModel
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.viewmodel.UserViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel by viewModels<MovieViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val args: HomeFragmentArgs by navArgs()

    //    private lateinit var userPreferences: UserPreferences
    private lateinit var viewModel: MainViewModel
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
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

//        viewModel.getUsername().observe(viewLifecycleOwner) {
//            binding.tvUsername.text = "Welcome, $it!"
//        }
        viewModel.getId().observe(viewLifecycleOwner) {
            val user = userViewModel.getUser(it)
            binding.tvUsername.text = "Welcome, ${user.username}!"
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