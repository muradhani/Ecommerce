package com.example.ecommerce.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.models.states.UserLoginState
import com.example.ecommerce.R
import com.example.ecommerce.activities.ShoppoingActivity
import com.example.ecommerce.databinding.FragmentLoginBinding
import com.example.ecommerce.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private  val viewModel: LoginViewModel by viewModels()
    lateinit var binding :FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       loginObserve()
    }

    private fun loginObserve() {
        viewModel.login.observe(viewLifecycleOwner, Observer { loginState ->
            if (loginState is UserLoginState.LoginSuccess){
                findNavController().navigate(R.id.action_loginFragment_to_shopping_navigation)
                Toast.makeText(requireActivity(),"success",Toast.LENGTH_SHORT).show()
            }
        })
    }

}