package com.example.taskmanager.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.RecyclerVerticalBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.view.ui.fragments.MainFragmentDirections

class TaskPriorityAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<TaskPriorityAdapter.TaskHolder>(){


    class TaskHolder(val verticalBinding: RecyclerVerticalBinding) : RecyclerView.ViewHolder(verticalBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val verticalBinding = RecyclerVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskHolder(verticalBinding)
    }
    override fun getItemCount(): Int {
        return  taskList.size
    }
    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.verticalBinding.headerText.text = taskList[position].taskName
        holder.verticalBinding.descriptionText.text = taskList[position].description
        holder.verticalBinding.dateText.text = taskList[position].date
        holder.verticalBinding.personCountText.text = taskList[position].personCount.toString() +" " + "Persons "
        val timeText = taskList[position].startTime + " - " + taskList[position].endTime
        holder.verticalBinding.timeText.text = timeText
        holder.verticalBinding.statementText.text = taskList[position].statement

        if(position % 3 == 0) {
            holder.verticalBinding.rectangleLine.setBackgroundResource(R.drawable.rectangle)
        }else if(position % 3 == 1){
            holder.verticalBinding.rectangleLine.setBackgroundResource(R.drawable.rectangle_blue)
        }else if(position % 3 == 2){
            holder.verticalBinding.rectangleLine.setBackgroundResource(R.drawable.rectangle_purple)
        }

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragment2ToEditFragment2(taskList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}

