package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.core.module.ANSWER
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.viewmodel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xeinebiu.suspend.dialogs.SuspendAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding:FragmentHomeBinding?=null;
    private val viewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bd = FragmentHomeBinding.inflate(inflater,container,false)
        binding = bd
        binding?.lifecycleOwner=this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel1=viewModel
        val bundle=Bundle()

        viewModel.movetopage.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.apply {
                bundle.putInt("id",-1)
                bundle.putString("body","")
                bundle.putString("title","")
                Navigation.findNavController(binding?.root!!).navigate(R.id.action_homeFragment_to_addNoteFragment,bundle)
            }
        }

        viewModel.datas.observe(viewLifecycleOwner){


            it.getContentIfNotHandled()?.apply {
                bundle.putInt("id",this.id)
                bundle.putString("body",this.Body)
                bundle.putString("title",this.title)
                Navigation.findNavController(binding?.root!!).navigate(R.id.action_homeFragment_to_addNoteFragment,bundle)
            }
        }

        viewModel.askNotify.observe(viewLifecycleOwner){
            val askData = it.getContentIfNotHandled()
            askData?.apply {
                lifecycleScope.launch {
                    val result = SuspendAlertDialog.confirm(
                        positiveButtonText = R.string.yes.string(),
                        negativeButtonText = R.string.no.string(),
                        neutralButtonText = R.string.cancel.string()
                    ) {
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Are you sure!")
                            .setMessage(askData.question)
                    }

// this line is executed after the dialog above is finish
                    val answer = result.toAnswer
                    Log.e("tag12", "onViewCreated: "+answer )
                    viewModel.onAskAnswered(askData,answer)
                }
            }
        }
    }

    val SuspendAlertDialog.DialogAction.toAnswer: ANSWER
    get(){
        return when(this){
            is SuspendAlertDialog.DialogAction.Positive->ANSWER.YES
            is SuspendAlertDialog.DialogAction.Negative->ANSWER.NO
            else->ANSWER.CANCEL
        }
    }

    override fun onDestroy() {
        binding=null
        viewModel.askNotify.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }



}