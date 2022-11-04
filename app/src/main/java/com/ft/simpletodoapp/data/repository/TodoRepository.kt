package com.ft.simpletodoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ft.simpletodoapp.data.dao.TodoDao
import com.ft.simpletodoapp.data.database.TodoDatabase
import com.ft.simpletodoapp.data.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TodoRepository(application: Application) {

    private val todoItemDao: TodoDao
    private val allTodoItems: LiveData<MutableList<TodoModel>>

    init {
        val database = TodoDatabase.getDatabase(application.applicationContext)
        todoItemDao = database.getDao()
        allTodoItems = todoItemDao.getAllTodoList()
    }

    fun saveTodoItems(todoItems: List<TodoModel>) = runBlocking {
        this.launch(Dispatchers.IO) {
            todoItemDao.saveTodoItems(todoItems)
        }
    }

    fun saveTodoItem(todoModel: TodoModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            todoItemDao.saveTodoItem(todoModel)
        }
    }

    fun updateTodoItem(todoModel: TodoModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            todoItemDao.updateTodoItem(todoModel)
        }
    }

    fun deleteTodoItem(todoModel: TodoModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            todoItemDao.deleteTodoItem(todoModel)
        }
    }

    fun getAllTodoList(): LiveData<MutableList<TodoModel>> {
        return allTodoItems
    }

}