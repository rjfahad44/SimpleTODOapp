package com.ft.simpletodoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoTable")
class TodoModel (
    @ColumnInfo(name = "title")
    var title :String,
    @ColumnInfo(name = "isCheck")
    var isCheck: Boolean = false) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}