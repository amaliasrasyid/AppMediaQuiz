package com.kontrakanprojects.appgamequiz.view.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.User
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest
import com.kontrakanprojects.appgamequiz.data.request.RegisterRequest
import com.kontrakanprojects.appgamequiz.data.session.UserPreference
import com.kontrakanprojects.appgamequiz.databinding.FragmentRegisterBinding
import com.kontrakanprojects.appgamequiz.util.Role
import com.kontrakanprojects.appgamequiz.util.Status
import com.kontrakanprojects.appgamequiz.util.mySnackBar
import com.kontrakanprojects.appgamequiz.util.textTrim
import com.kontrakanprojects.appgamequiz.view.profile.ProfileViewModel

class RegisterFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: ProfileViewModel by viewModels()


    private val textWatcherName = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(input: Editable?) {
            if(input.isNullOrEmpty()){
                binding.tiName.error = getString(R.string.msg_name_not_null)
            }else{
                binding.tiName.error = null
            }
        }
    }
    private val textWatcherEmail = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(input: Editable?) {
            if(input.isNullOrEmpty()){
                binding.tiEmail.error = getString(R.string.msg_email_not_null)
            }else{
                binding.tiEmail.error = null
            }
        }
    }
    private val textWatcherPsw = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(input: Editable?) {
            if(input.isNullOrEmpty()){
                binding.tiPassword.error = getString(R.string.msg_psw_not_null)
            }else if(input.length < 6){
                binding.tiPassword.error = getString(R.string.msg_psw_min_character)
            }else{
                binding.tiPassword.error = null
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnRegister.setOnClickListener(this@RegisterFragment)
            tvBackToLogin.setOnClickListener(this@RegisterFragment)
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                btnRegister -> {
                    edtName.addTextChangedListener(textWatcherName)
                    edtEmail.addTextChangedListener(textWatcherEmail)
                    edtPassword.addTextChangedListener(textWatcherPsw)
                    if(!rbGuru.isChecked && !rbSiswa.isChecked) {
                        view?.mySnackBar(getString(R.string.msg_role_not_null))
                        return@with
                    }
                    registerUser()
                }
                tvBackToLogin -> findNavController().navigateUp()
                else -> {}
            }
        }
    }

    private fun registerUser(){
        val request = getRequest()
        observableViewModel(request)
    }

    private fun observableViewModel(request: RegisterRequest) {
        viewModel.store(request).observe(viewLifecycleOwner){ response ->
            when(response.status){
                Status.LOADING -> loader(true)
                Status.SUCCESS -> {
                    loader(false)
                    findNavController().navigateUp()
                }
                Status.ERROR -> {
                    loader(false)
                    view?.mySnackBar(response.message.toString())
                }
            }
        }
    }

    private fun getRequest(): RegisterRequest {
        with(binding){
            val name = edtName.textTrim()
            val email = edtEmail.textTrim()
            val password = edtPassword.textTrim()
            val role = checkedRole()
            return RegisterRequest(0,name,email,password,role)
        }
    }

    private fun checkedRole(): Int {
        with(binding){
            when{
                rbGuru.isChecked -> return Role.GURU.id
                else -> return Role.SISWA.id
            }
        }
    }

    private fun loader(state: Boolean) {
        with(binding) {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}