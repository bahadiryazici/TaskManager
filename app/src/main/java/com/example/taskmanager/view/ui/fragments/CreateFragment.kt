package com.example.taskmanager.view.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.view.adapter.ParticipantAdapter
import com.example.taskmanager.R
import com.example.taskmanager.util.Util
import com.example.taskmanager.databinding.FragmentCreateBinding
import com.example.taskmanager.model.Participant
import com.example.taskmanager.model.Task
import com.example.taskmanager.viewmodel.TaskViewModel


class CreateFragment : Fragment() {

    private var _binding : FragmentCreateBinding?= null
    private val binding get() = _binding!!
    private val participantList = arrayListOf<Participant>()
    private val participantAdapter = ParticipantAdapter(participantList)
    private var participantCount = 0
    private var addCategoryView : View ?= null
    private val categoryList = arrayListOf<String>()
    private lateinit var taskViewModel: TaskViewModel
    private val util = Util()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreateBinding.inflate(layoutInflater,container,false)

        init()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        binding.button2.setOnClickListener {
            addTask()
        }

        binding.imageView2.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.addCategory.setOnClickListener {
            util.alertDialog(categoryList,requireContext(),addCategoryView!!,
                binding.categorySpinner.adapter as ArrayAdapter<String>
            )
        }

        binding.addParticipant.setOnClickListener {
            util.addParticipant(participantList,participantAdapter)
        }

        binding.datePickerButton.setOnClickListener {
            util.initDatePicker(requireContext(),binding.datePickerButton).show()
        }
    }

    private fun init(){
        binding.participantRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.participantRecycler.adapter = participantAdapter
        binding.datePickerButton.text = util.getTodayDate()
        addCategoryView = LayoutInflater.from(context).inflate(R.layout.add_category,null)
        binding.categorySpinner.adapter= util.categorySpinner(categoryList,requireContext())
    }

    private fun addTask(){

        if(binding.participantRecycler.adapter!=null){
            participantCount = binding.participantRecycler.adapter!!.itemCount
        }
        if(binding.taskName.text.toString() == "" ){
            binding.taskName.error = "This field need to fill up"

        }else if( binding.description.text.toString() == ""){
            binding.description.error = "This field need to fill up"

        }else{
            val task : Task
            with(binding) {
                task = Task(
                    taskName.text.toString(),
                    datePickerButton.text.toString(),
                    startTime.selectedItem.toString(),
                    endTime.selectedItem.toString(),
                    categorySpinner.selectedItem.toString(),
                    prioritySpinner.selectedItem.toString(),
                    participantCount,
                    description.text.toString(),
                    null, "New",
                    0
                )
            }
            taskViewModel.addTask(task)
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}