package com.example.taskmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.RecyclerTaskPersonCountBinding
import com.example.taskmanager.model.Participant

class PartiCountAdapter(private val items : List<Participant>) : RecyclerView.Adapter<PartiCountAdapter.PartiCountHolder>() {
    class PartiCountHolder(val partiCountBinding: RecyclerTaskPersonCountBinding) : RecyclerView.ViewHolder(partiCountBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiCountHolder {
        val partiCountBinding = RecyclerTaskPersonCountBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PartiCountHolder(partiCountBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PartiCountHolder, position: Int) {

        val currentItem = items[position]
        holder.partiCountBinding.imageView5.setImageResource(currentItem.imageResource)
    }
}