package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.todoapp.databinding.FragmentAddNoteBinding
import com.example.todoapp.viewmodel.AddNoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment :Fragment() {
    private var binding:FragmentAddNoteBinding?=null
    private val viewmodel:AddNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewmodel)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddNoteBinding.inflate(inflater,container,false)
        binding?.lifecycleOwner=this
        return binding?.root


    }

    override fun onDestroy() {
        binding=null
        super.onDestroy()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addviewmodel=viewmodel

        val bundle=arguments
        viewmodel.getBundle(bundle!!)

        viewmodel.movePage.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.apply {
                Navigation.findNavController(binding?.root!!).navigate(R.id.action_addNoteFragment_to_homeFragment)
            }
        }
    }



}