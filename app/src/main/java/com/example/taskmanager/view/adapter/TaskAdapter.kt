package com.example.taskmanager.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.RecyclerHorizontalBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.view.ui.fragments.MainFragmentDirections
import kotlin.random.Random

class TaskAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    class TaskHolder(val horizontalBinding: RecyclerHorizontalBinding) : RecyclerView.ViewHolder(horizontalBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val horizontalBinding = RecyclerHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskHolder(horizontalBinding)
    }
    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.horizontalBinding.headerText.text = taskList[position].taskName
        holder.horizontalBinding.descriptionText.text = taskList[position].description
        holder.horizontalBinding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                holder.horizontalBinding.seekBarText.text = "%$progress"
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        val priorityLevel = taskList[position].priority?.toInt()
        if (priorityLevel == 1 || priorityLevel == 2) {
            holder.horizontalBinding.urgentText.text = "Urgent"
            holder.horizontalBinding.urgentText.setBackgroundResource(R.drawable.rounded_corner)
        } else if (priorityLevel == 3 || priorityLevel == 4) {
            holder.horizontalBinding.urgentText.text = "Normal"
            holder.horizontalBinding.urgentText.setBackgroundResource(R.drawable.rounded_corner_blue)
        } else {
            holder.horizontalBinding.urgentText.text = "No Urgency"
            holder.horizontalBinding.urgentText.setBackgroundResource(R.drawable.rounded_corner_purple)
        }
        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragment2ToEditFragment2(taskList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
        val myRandomValue = Random.nextInt(0,100)
        holder.horizontalBinding.seekBar.progress = myRandomValue
        holder.horizontalBinding.seekBarText.text = "%$myRandomValue"
    }
    override fun getItemCount(): Int {
        return taskList.size
    }
}








