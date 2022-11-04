package com.ft.simpletodoapp.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ft.simpletodoapp.R
import com.ft.simpletodoapp.interfaces.TodoInterface
import com.ft.simpletodoapp.adapter.RvAdapter
import com.ft.simpletodoapp.data.model.TodoModel
import com.ft.simpletodoapp.data.viewmodel.TodoViewModel
import com.ft.simpletodoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TodoInterface.ItemClickInterface, TodoInterface.ItemCheckStateInterface,
    TodoInterface.ItemLongPressInterface {

    private lateinit var viewModal: TodoViewModel
    private lateinit var adapter: RvAdapter
    private lateinit var dialog: Dialog
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TodoViewModel::class.java]


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RvAdapter(this, this, this)
        binding.recyclerView.adapter = adapter

        viewModal.getAllTodoItemList().observe(this){
            adapter.updateList(it)
        }

        binding.floatingActionButton.setOnClickListener {
            AddEditFragment().show(supportFragmentManager, "newTodoItem")
        }
    }

    override fun onItemChecked(todoModel: TodoModel) {
        viewModal.itemCheckState(todoModel)
    }

    override fun onItemLongPress(todoModel: TodoModel) {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_item_delete)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.findViewById<TextView>(R.id.tv_yes).setOnClickListener {
            viewModal.deleteTodoItem(todoModel)
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.tv_no).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onItemClick(todoModel: TodoModel) {
        Log.d("TEST", "Check : ${todoModel.title}")
        viewModal.model.value = todoModel
        AddEditFragment().show(supportFragmentManager, "UpdateTodoItem")
    }
}