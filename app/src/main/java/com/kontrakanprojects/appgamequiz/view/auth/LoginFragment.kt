package com.kontrakanprojects.appgamequiz.view.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.User
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest
import com.kontrakanprojects.appgamequiz.data.session.UserPreference
import com.kontrakanprojects.appgamequiz.databinding.FragmentLoginBinding
import com.kontrakanprojects.appgamequiz.util.Status
import com.kontrakanprojects.appgamequiz.util.mySnackBar
import com.kontrakanprojects.appgamequiz.util.textTrim
import com.kontrakanprojects.appgamequiz.view.MainActivity

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModels()

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
    private val textWatcherPsw = object: TextWatcher{
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
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnLogin.setOnClickListener(this@LoginFragment)
            tvToRegister.setOnClickListener(this@LoginFragment)
        }
    }

    override fun onClick(view: View?) {
       with(binding){
           when(view){
               btnLogin -> {
                   edtEmail.addTextChangedListener(textWatcherEmail)
                   edtPassword.addTextChangedListener(textWatcherPsw)
                   val email = edtEmail.textTrim()
                   val password = edtPassword.textTrim()
                   when{
                       email.isEmpty() -> tiEmail.error = getString(R.string.msg_email_not_null)
                       password.isEmpty() -> tiPassword.error = getString(R.string.msg_psw_not_null)
                       else -> authenticateUser()
                   }
               }
               tvToRegister -> moveToRegister()
           }
       }
    }

    private fun moveToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun authenticateUser() {
        val request = getRequest()
        observableViewModel(request)
    }

    private fun observableViewModel(request: LoginRequest) {
        viewModel.login(request).observe(viewLifecycleOwner){ response -> 
            when(response.status){
                Status.LOADING -> loader(true)
                Status.SUCCESS -> {
                    loader(false)
                    UserPreference(requireContext()).apply {
                        val user = User(
                            id = response.data?.id,
                            name = response.data?.nama,
                            email = response.data?.email,
                            password = response.data?.kataSandi,
                            role = response.data?.role
                        )
                        setUser(user)
                        setLogin(true)
                    }
                    moveToMainActivity()
                }
                Status.ERROR -> {
                    loader(false)
                    view?.mySnackBar(response.message.toString())
                }
            }
        }
    }

    private fun getRequest(): LoginRequest {
        with(binding){
            val email = edtEmail.textTrim()
            val password = edtPassword.textTrim()
            return LoginRequest(email,password)
        }
    }

    private fun moveToMainActivity() {
        startActivity(Intent(requireActivity(),MainActivity::class.java))
        requireActivity().finish()
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