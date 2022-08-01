package com.example.retrofitdemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.retrofitdemo.databinding.FragmentExampleBinding
import com.example.retrofitdemo.model.ProductInfo
import com.example.retrofitdemo.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExampleFragment : Fragment() {

    private var _binding: FragmentExampleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        onClickListeners()

        observers()

    }
    private fun init() {
        viewModel.getData()
    }

    private fun onClickListeners() {
        binding.showFullDataButton.setOnClickListener {
            findNavController().navigate(ExampleFragmentDirections.toFullDataFragment())
        }
    }

    private fun observers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.data.collect {
                setData(it)
            }
        }
    }

    private fun setData(productInfo: ProductInfo) = with(binding) {
        Glide.with(requireActivity())
            .load(productInfo.equipment.equipmentMedia[0].files[0].path)
            .into(productImageView)
        titleTextView.text = productInfo.equipment.title
        yearTextView.text = productInfo.equipment.year.toString()
        addressTextView.text = productInfo.address
        statusTextView.text = productInfo.status
        typeTextView.text = productInfo.type
        hasDriverCheckBox.isChecked = productInfo.hasDriver
    }

}