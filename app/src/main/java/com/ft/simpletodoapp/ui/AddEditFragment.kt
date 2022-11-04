package com.ft.simpletodoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ft.simpletodoapp.data.model.TodoModel
import com.ft.simpletodoapp.data.viewmodel.TodoViewModel
import com.ft.simpletodoapp.databinding.FragmentAddEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddEditFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddEditBinding
    private lateinit var viewModal: TodoViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
        )[TodoViewModel::class.java]

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnSave.setOnClickListener {
            viewModal.saveTodoItem(TodoModel(
                binding.tieTitle.text.toString(),
                false
            ))
            binding.tieTitle.setText("")
            dismiss()
        }


        viewModal.model.observe(this){ updateTodo->
            Log.d("UPDATE", "Success : ${updateTodo.title}")
            binding.btnSave.text = "Update"
            binding.tilTitle.hint = "Update your task name"
            binding.tieTitle.setText(updateTodo.title)
            binding.btnSave.setOnClickListener {
                updateTodo.title = binding.tieTitle.text.toString()
                viewModal.updateTodoItem(updateTodo)
            }
            binding.btnCancel.setOnClickListener { dismiss() }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }
}