package com.ft.simpletodoapp.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ft.simpletodoapp.R
import com.ft.simpletodoapp.interfaces.TodoInterface
import com.ft.simpletodoapp.data.model.TodoModel
import com.google.android.material.card.MaterialCardView

class RvAdapter(
    private val itemClickInterface: TodoInterface.ItemClickInterface,
    private val itemCheckStateInterface: TodoInterface.ItemCheckStateInterface,
    private val itemLongPressInterface: TodoInterface.ItemLongPressInterface
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private val todoList = ArrayList<TodoModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val imageView = itemView.findViewById<ImageView>(R.id.imgV)
        val cv_isCheck = itemView.findViewById<MaterialCardView>(R.id.cv_isCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = todoList[position].title
        setTextStrike(holder, todoList[position])

        holder.itemView.setOnClickListener {
            itemClickInterface.onItemClick(todoList[position])
        }

        holder.itemView.setOnLongClickListener {
            itemLongPressInterface.onItemLongPress(todoList[position])
            true
        }

        holder.cv_isCheck.setOnClickListener {
            itemCheckStateInterface.onItemChecked(todoList[position])
        }
    }

    private fun setTextStrike(holder: ViewHolder, todoModel: TodoModel) {
        if (todoModel.isCheck){
            holder.imageView.visibility = View.VISIBLE
            holder.title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.title.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.hint_text_color))
        }else{
            holder.imageView.visibility = View.INVISIBLE
            holder.title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.title.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.text_color))
        }
    }


    override fun getItemCount(): Int {
        return todoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<TodoModel>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

}
