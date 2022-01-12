package com.example.todoapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.room.Delete
import com.example.todoapp.databinding.DeletechatdialogueBinding
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.viewmodel.DialogueViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class DeleteChatDialogueFragment():AppCompatDialogFragment() {
    private var binding: DeletechatdialogueBinding?=null
    private val viewModel:DialogueViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.dialogueviewmodel=viewModel

        viewModel.dismiss.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.apply {
                dismiss()
            }
        }





    }

    override fun onDestroy() {
        binding=null
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bd = DeletechatdialogueBinding.inflate(inflater,container,false)
        binding = bd
        binding?.lifecycleOwner=this
        return binding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure!")
        builder.setMessage("Do you want to close this?")


        val bundle=arguments





        builder.setView(binding?.root).setPositiveButton("ok",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                viewModel.onSuccesspressed(bundle?.getInt("id")!!)
            }

        })
        builder.setView(binding?.root).setNegativeButton("cancel",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
               viewModel.onCancelpressed()
            }

        })






        return builder.create()
    }

}