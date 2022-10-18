package com.kontrakanprojects.appgamequiz.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.session.UserPreference
import com.kontrakanprojects.appgamequiz.databinding.FragmentUserProfileBinding
import com.kontrakanprojects.appgamequiz.util.TEACHER

class UserProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = UserPreference(requireContext())
        val user = sharedPrefs.getUser()

        with(binding){
            btnExit.setOnClickListener(this@UserProfileFragment)
            val role = if(user.role == TEACHER) "GURU" else "SISWA"
            edtName.setText(user.name)
            edtRole.setText(role)
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                btnExit -> findNavController().navigateUp()
                else -> {}
            }
        }
    }
}