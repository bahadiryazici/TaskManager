package com.example.taskmanager.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.view.adapter.TaskCategoryAdapter.*
import com.example.taskmanager.R
import com.example.taskmanager.databinding.RecyclerTasksBinding
import com.example.taskmanager.model.Participant
import com.example.taskmanager.model.Task
import kotlin.random.Random

class TaskCategoryAdapter(private val taskList : List<Task>) : RecyclerView.Adapter<TaskCategoryHolder>() {
    class TaskCategoryHolder(val taskCategory : RecyclerTasksBinding) : RecyclerView.ViewHolder(taskCategory.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCategoryHolder {
        val taskCategory = RecyclerTasksBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskCategoryHolder(taskCategory)
    }
    override fun onBindViewHolder(holder: TaskCategoryHolder, position: Int) {
        holder.taskCategory.headerText.text = taskList[position].category
        holder.taskCategory.descriptionText.text = taskList[position].taskName
        holder.taskCategory.taskCountText.text =  taskList[position].count + " " + "Tasks"
        holder.taskCategory.dateText.text = taskList[position].date
        val partiCountList = arrayListOf<Participant>()
        val adapter = PartiCountAdapter(partiCountList)
        holder.taskCategory.taskCountRecycler.layoutManager =
            LinearLayoutManager(holder.taskCategory.taskCountText.context, LinearLayoutManager.HORIZONTAL,false)
        for(i in 0 until (taskList[position].personCount!!)){
            val partiCount = Participant(R.drawable.baseline_person_24)
            partiCountList.add(0, partiCount)
        }
        holder.taskCategory.taskCountRecycler.adapter = adapter
        adapter.notifyItemInserted(partiCountList.size-1)
        val myRandomValue = Random.nextInt(0,100)
        if(position % 3 == 0) {
            holder.taskCategory.rectangleLine.setBackgroundResource(R.drawable.rectangle)
            holder.taskCategory.progressBar.progress = myRandomValue
            holder.taskCategory.progressBarText.text = "%$myRandomValue"
        }else if(position % 3 == 1){
            holder.taskCategory.rectangleLine.setBackgroundResource(R.drawable.rectangle_blue)
            holder.taskCategory.progressBar.progress = myRandomValue
            holder.taskCategory.progressBarText.text = "%$myRandomValue"
        }else if(position % 3 == 2){
            holder.taskCategory.rectangleLine.setBackgroundResource(R.drawable.rectangle_purple)
            holder.taskCategory.progressBar.progress = myRandomValue
            holder.taskCategory.progressBarText.text = "%$myRandomValue"
        }
    }
    override fun getItemCount(): Int {
        return taskList.size
    }
}