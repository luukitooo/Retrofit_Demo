package com.example.retrofitdemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.retrofitdemo.R
import com.example.retrofitdemo.databinding.FragmentFullDataBinding
import com.example.retrofitdemo.viewmodel.MainViewModel
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullDataFragment : Fragment() {

    private var _binding: FragmentFullDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        observers()

    }

    private fun init() {
        viewModel.getData()
    }

    private fun observers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.data.collect {
                binding.dataTextView.text = it.toString()
            }
        }
    }
}