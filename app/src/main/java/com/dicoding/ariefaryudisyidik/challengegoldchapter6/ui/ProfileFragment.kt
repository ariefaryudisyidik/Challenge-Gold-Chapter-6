package com.dicoding.ariefaryudisyidik.challengegoldchapter6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.R
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local.User
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.databinding.FragmentProfileBinding
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.MainViewModel
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.UserDataStoreManager
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.UserPreferences
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.ViewModelFactory
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.viewmodel.UserViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var userPreferences: UserPreferences
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: UserDataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreferences(requireContext())
        pref = UserDataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        showProfile()


        binding.ivProfile.setOnClickListener {

        }

        binding.btnUpdate.setOnClickListener {
            updateProfile()
        }

        binding.btnLogout.setOnClickListener {
//            viewModel.logoutUser()
            userPreferences.clearLoggedInUser()
            findNavController().navigate(
                R.id.action_profileFragment_to_loginFragment,
            )
        }
    }

    private fun updateProfile() {
        viewModel.getId().observe(viewLifecycleOwner) {
            val user = userViewModel.getUser(it)
            binding.apply {
                val updateUser = User(
                    id = user.id,
                    email = user.email,
                    password = user.password,
                    username = edtUsername.text.toString(),
                    fullName = edtFullName.text.toString(),
                    dateOfBirth = edtDateOfBirth.text.toString(),
                    address = edtAddress.text.toString()
                )
                reset()
                userViewModel.update(updateUser)
                Toast.makeText(
                    requireContext(),
                    "Profile was successfully updated",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun reset() {
        binding.apply {
            edtUsername.clearFocus()
            edtFullName.clearFocus()
            edtDateOfBirth.clearFocus()
            edtAddress.clearFocus()
        }
    }

    private fun showProfile() {
        viewModel.getId().observe(viewLifecycleOwner) {
            val user = userViewModel.getUser(it)
            binding.apply {
                edtUsername.setText(user.username)
                edtFullName.setText(user.fullName)
                edtDateOfBirth.setText(user.dateOfBirth)
                edtAddress.setText(user.address)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}