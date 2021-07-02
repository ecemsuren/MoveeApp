package com.example.moveeapp.ui.modules.login.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiSessionSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiUserSingleton
import com.example.moveeapp.data.model.tmdb.CreateSession
import com.example.moveeapp.data.model.tmdb.UserInfo
import com.example.moveeapp.databinding.FragmentLoginBinding
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.modules.login.base.LoginBaseFragment
import com.example.moveeapp.ui.modules.login.viewmodel.LoginViewModel

class LoginFragment : LoginBaseFragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        loginViewModel = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)

        setLinkToTheTextView()

        registerLiveData()

        initClicks()
        return view
    }


    private fun registerLiveData() {
        loginViewModel.guestResponse.observe(viewLifecycleOwner, Observer { guestResponse ->
            guestResponse?.let { guest ->
                if (guest.success) {
                    TMDBApiSessionSingleton.sessionId = guest.guestSessionId
                    dismissProgress()
                    navigateToMainFragment()


                }

            }
        })

        loginViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let { errorMessage ->
                alertForError(errorMessage)
            }
        })

        loginViewModel.createTokenResponse.observe(
            viewLifecycleOwner,
            Observer { requestTokenResponse ->
                requestTokenResponse?.let { requestToken ->
                    if (requestToken.success) {
                        LoginViewModel.requestToken = requestToken.requestToken
                        loginViewModel.getSessionWithLogin(
                            UserInfo(
                                binding.loginUserName.text.toString(),
                                binding.loginPassword.text.toString(),
                                requestToken.requestToken
                            )
                        )
                    }
                }
            })

        loginViewModel.sessionWithLoginResponse.observe(
            viewLifecycleOwner,
            Observer { sessionWithLoginResponse ->
                sessionWithLoginResponse?.let { sessionWithLogin ->
                    if (sessionWithLogin.success) {
                        loginViewModel.createSession(
                            CreateSession(
                                sessionWithLogin.requestToken
                            )
                        )
                    }
                }
            })

        loginViewModel.createSessionResponse.observe(
            viewLifecycleOwner,
            Observer { createSessionResponse ->
                createSessionResponse?.let { createSession ->
                    if (createSession.success) {
                        TMDBApiSessionSingleton.sessionId = createSession.sessionId
                        dismissProgress()
                        navigateToMainFragment()
                    }

                }

            })
    }

    private fun initClicks() {
        binding.loginBtnGuest.setOnClickListener {
            TMDBApiUserSingleton.isGuest = true
            loginViewModel.getGuest()
            showProgress()

        }

        binding.loginBtnLogin.setOnClickListener {
            TMDBApiUserSingleton.isGuest = false
            loginViewModel.getCreateToken()
            showProgress()

        }
    }

    private fun navigateToMainFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun setLinkToTheTextView() {

        binding.loginTxtRegisterNow.movementMethod = LinkMovementMethod.getInstance()
        binding.loginTxtForgotPassword.movementMethod = LinkMovementMethod.getInstance()

    }

}