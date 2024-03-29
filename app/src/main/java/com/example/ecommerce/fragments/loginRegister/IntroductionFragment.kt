package com.example.ecommerce.fragments.loginRegister

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentIntroductionBinding
import com.example.ecommerce.viewModels.IntroductionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroductionFragment : Fragment() {
    private val viewModel: IntroductionViewModel by viewModels()
    private lateinit var binding : FragmentIntroductionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_introduction,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkifIntroductionFragmentOpenedBefore()
        initStartButton()
    }

    private fun checkifIntroductionFragmentOpenedBefore() {
        viewModel.showOnboarding.observe(viewLifecycleOwner, Observer {
            if (!it){
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.introductionFragment, true)
                    .build()

                findNavController().navigate(R.id.action_introductionFragment_to_loginRegisterFragment, null, navOptions)
            }
        })
    }

    private fun initStartButton() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_introductionFragment_to_loginRegisterFragment)
            viewModel.markOnboardingAsShown()
        }
    }
}