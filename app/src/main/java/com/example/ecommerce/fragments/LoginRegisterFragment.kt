package com.example.ecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentLoginRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginRegisterFragment : Fragment() {
    private lateinit var binding : FragmentLoginRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_register,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoginButton()
        initRegisterButton()
    }

    private fun initRegisterButton() {
       binding.btnRegister.setOnClickListener {
           findNavController().navigate(R.id.action_loginRegisterFragment_to_registerFragment)
       }
    }

    private fun initLoginButton() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginRegisterFragment_to_loginFragment)
        }
    }

}