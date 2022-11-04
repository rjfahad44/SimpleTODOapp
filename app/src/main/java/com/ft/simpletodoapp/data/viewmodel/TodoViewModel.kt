package com.ft.simpletodoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ft.simpletodoapp.data.model.TodoModel
import com.ft.simpletodoapp.data.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    var model = MutableLiveData<TodoModel>()

    private val repository: TodoRepository = TodoRepository(application)
    private val todoItems: LiveData<MutableList<TodoModel>> = repository.getAllTodoList()

    fun saveTodoItem(todoModel: TodoModel) {
        repository.saveTodoItem(todoModel)
    }

    fun saveTodoItems(todoItems: List<TodoModel>) {
        repository.saveTodoItems(todoItems)
    }

    fun updateTodoItem(todoModel:TodoModel) {
        repository.updateTodoItem(todoModel)
    }



    fun deleteTodoItem(todoModel: TodoModel) {
        repository.deleteTodoItem(todoModel)
    }

    fun itemCheckState(todoModel: TodoModel) {
        todoModel.isCheck = !todoModel.isCheck
        repository.updateTodoItem(todoModel)
    }

    fun getAllTodoItemList(): LiveData<MutableList<TodoModel>> {
        return todoItems
    }
}