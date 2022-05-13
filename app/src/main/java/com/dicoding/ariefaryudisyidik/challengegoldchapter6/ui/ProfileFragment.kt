package com.dicoding.ariefaryudisyidik.challengegoldchapter6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.R
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local.User
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.databinding.FragmentProfileBinding
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper.UserPreferences

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var userPreferences: UserPreferences

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

        binding.btnLogout.setOnClickListener {
            userPreferences.clearLoggedInUser()
            findNavController().navigate(
                R.id.action_profileFragment_to_loginFragment,
            )
        }
    }

    private fun showProfile(data: User) {
        binding.apply {
            edtUsername.setText(data.username)
            edtFullName.setText(data.fullName)
            edtDateOfBirth.setText(data.dateOfBirth)
            edtAddress.setText(data.address)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}