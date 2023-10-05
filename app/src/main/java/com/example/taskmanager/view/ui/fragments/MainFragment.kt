package com.example.taskmanager.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.view.adapter.TaskAdapter
import com.example.taskmanager.view.adapter.TaskPriorityAdapter
import com.example.taskmanager.databinding.FragmentMainBinding
import com.example.taskmanager.viewmodel.TaskViewModel
import com.google.android.material.tabs.TabLayout
import java.util.Calendar


class MainFragment : Fragment() {


    private var _binding : FragmentMainBinding ?= null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        goodMorning()
        getFromDatabaseByDate()
        tabClick()
    }

    @SuppressLint("SetTextI18n")
    private fun goodMorning(){
        Log.i("TAG", "goodMorning: ")
        val calendar = Calendar.getInstance()

        when(calendar.get(Calendar.HOUR_OF_DAY)){

            6,7,8,9,10,11,12-> binding.textView8.text = "Good Morning"
            13,14,15,16,17,18->binding.textView8.text = "Good Afternoon"
            19,20,21,22,23 -> binding.textView8.text = "Good Evening"
            1,2,3,4,5 -> binding.textView8.text = "Good Night"
        }
    }

    private fun getFromDatabaseByStatement(statement : String) {

        when(statement){
            "On Going"->  taskViewModel.taskLiveDataByStatement.observe(viewLifecycleOwner) {
                it?.let {
                    val taskAdapter = TaskAdapter(it)
                    binding.horizontalRecycler.adapter = taskAdapter
                    with(binding) {
                        if (it.isEmpty()) {
                            OnGoingTextView.text = "There is no On Going task"
                            OnGoingTextView.visibility = View.VISIBLE
                        } else {
                            OnGoingTextView.visibility = View.GONE
                        }
                    }
                }
            }

            "Completed"->taskViewModel.taskLiveDataByStatement.observe(viewLifecycleOwner) {
                it?.let {
                    val taskAdapter = TaskAdapter(it)
                    binding.horizontalRecycler.adapter = taskAdapter
                    with(binding) {
                        if (it.isEmpty()) {
                            OnGoingTextView.text = "There is no Completed task"
                            OnGoingTextView.visibility = View.VISIBLE
                        } else {
                            OnGoingTextView.visibility = View.GONE
                        }
                    }
                }
            }
        }
        taskViewModel.getStatement(statement)

    }

    private fun getFromDatabaseByDate(){
        taskViewModel.taskLiveDataByDate.observe(viewLifecycleOwner) {
            it?.let {
                val taskPriorityAdapter = TaskPriorityAdapter(it)
                binding.verticalRecycler.adapter = taskPriorityAdapter

                with(binding) {
                    if (it.isEmpty()) {
                        emptyTextView.visibility = View.VISIBLE
                        verticalRecycler.visibility = View.INVISIBLE
                        taskPriority.visibility = View.INVISIBLE
                    } else {
                        emptyTextView.visibility = View.GONE
                        verticalRecycler.visibility = View.VISIBLE
                        taskPriority.visibility = View.VISIBLE
                    }
                }
            }
        }
        taskViewModel.getByDate()
    }

    private fun getFromDatabase(){
        taskViewModel.taskLiveData.observe(viewLifecycleOwner) {
            it?.let {
                val taskAdapter = TaskAdapter(it)
                binding.horizontalRecycler.adapter = taskAdapter
                binding.OnGoingTextView.visibility = View.GONE
            }
        }
        taskViewModel.getDataFromRoom()
    }

    private fun tabClick(){

        val tab = binding.tabLayout2.getTabAt(0)
        tab?.select()
        getFromDatabase()
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        getFromDatabase()
                    }
                    1 -> {
                        getFromDatabaseByStatement("On Going")
                    }
                    2 -> {
                        getFromDatabaseByStatement("Completed")
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




