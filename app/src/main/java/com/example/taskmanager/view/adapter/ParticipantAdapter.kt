package com.example.taskmanager.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.RecyclerParticipantBinding
import com.example.taskmanager.model.Participant

class ParticipantAdapter(private val items : List<Participant>) : RecyclerView.Adapter<ParticipantAdapter.RecyclerHolder>() {



    class RecyclerHolder(val participantBinding: RecyclerParticipantBinding) : RecyclerView.ViewHolder(participantBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val participantBinding = RecyclerParticipantBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  RecyclerHolder(participantBinding)
    }




    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {

        val currentItem = items[position]

        holder.participantBinding.imageView5.setImageResource(currentItem.imageResource)

    }

    override fun getItemCount(): Int {
        return  items.size
    }



}