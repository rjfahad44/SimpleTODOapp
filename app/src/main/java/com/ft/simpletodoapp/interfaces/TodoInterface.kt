package com.ft.simpletodoapp.interfaces

import com.ft.simpletodoapp.data.model.TodoModel

class TodoInterface {
    interface ItemCheckStateInterface {
        fun onItemChecked(todoModel: TodoModel)
    }

    interface ItemClickInterface {
        fun onItemClick(todoModel: TodoModel)
    }

    interface ItemLongPressInterface {
        fun onItemLongPress(todoModel: TodoModel)
    }
}