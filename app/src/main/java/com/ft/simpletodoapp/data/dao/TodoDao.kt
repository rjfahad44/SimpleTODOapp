package com.ft.simpletodoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.ft.simpletodoapp.data.model.TodoModel

@Dao
interface TodoDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveTodoItem(todoModel: TodoModel)

    @Insert
    suspend fun saveTodoItems(todoModel: List<TodoModel>)

    @Delete
    suspend fun deleteTodoItem(todoModel: TodoModel)

    @Update
    suspend fun updateTodoItem(todoModel: TodoModel)

    @Query("SELECT * FROM TodoTable ORDER BY id DESC")
    fun getAllTodoList(): LiveData<MutableList<TodoModel>>
}