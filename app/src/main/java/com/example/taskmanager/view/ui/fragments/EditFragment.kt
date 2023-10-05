package com.example.taskmanager.view.ui.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.R
import com.example.taskmanager.util.Util
import com.example.taskmanager.view.adapter.ParticipantAdapter
import com.example.taskmanager.databinding.FragmentEditBinding
import com.example.taskmanager.model.Participant
import com.example.taskmanager.model.Task
import com.example.taskmanager.viewmodel.TaskViewModel

class EditFragment : Fragment() {
    private val participantList = arrayListOf<Participant>()
    private val participantAdapter = ParticipantAdapter(participantList)
    private var addCategoryView : View ?= null
    private val categoryList = arrayListOf<String>()
    private lateinit var taskViewModel: TaskViewModel
    private var taskId = 0
    private var taskParticipantCount = 0
    private val util = Util()
    private lateinit var dataBinding: FragmentEditBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit,container,false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        init()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.datePickerButton.setOnClickListener {
           util.initDatePicker(requireContext(),dataBinding.datePickerButton).show()
        }
        dataBinding.backImage.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        dataBinding.addCategory2.setOnClickListener {
            util.alertDialog(categoryList,requireContext(),addCategoryView!!,
                dataBinding.categorySpinner2.adapter as ArrayAdapter<String>
            )
        }
        dataBinding.button2.setOnClickListener {
            editTask()
        }
        dataBinding.participantButton.setOnClickListener {
            util.addParticipant(participantList, adapter = participantAdapter)
        }
    }

    private fun init() {
        addCategoryView = LayoutInflater.from(context).inflate(R.layout.add_category,null)
        dataBinding.categorySpinner2.adapter= util.categorySpinner(categoryList,requireContext())
        dataBinding.participantRecycler.adapter = participantAdapter

        arguments?.let {

            taskId = EditFragmentArgs.fromBundle(it).id
        }
        if (taskId == 0 )
            taskId = 1

        observeLiveData(taskId)

    }

    private fun observeLiveData(id: Int) {
        taskViewModel.getDataByID(id)
        taskViewModel.taskLiveDataByID.observe(viewLifecycleOwner) {
            it?.let { task ->

                dataBinding.selectedTask = task

                taskParticipantCount = task.personCount!!
                val participant = Participant(R.drawable.baseline_person_24)
                for (i in 0 until taskParticipantCount)
                    participantList.add(0, participant)
                participantAdapter.notifyItemInserted(participantList.size - 1)
            }
        }
    }

    private fun editTask(){
        arguments?.let {

            taskId = EditFragmentArgs.fromBundle(it).id
        }

        if(dataBinding.taskNameEditText.text.toString() == "" ){
            dataBinding.taskNameEditText.error = "This field need to fill up"

        }else if( dataBinding.description.text.toString() == "") {
            dataBinding.description.error = "This field need to fill up"
        }else{
            val task : Task
            with(dataBinding){
                     task = Task(taskNameEditText.text.toString(),
                    datePickerButton.text.toString(),
                    startTimeSpin.selectedItem.toString(),
                    endTimeSpinner.selectedItem.toString(),
                    categorySpinner2.selectedItem.toString(),
                    prioritySpinner3.selectedItem.toString(),
                    participantRecycler.adapter?.itemCount,
                    descriptionEditText.text.toString(),
                    null,
                    statementSpinner.selectedItem.toString(),
                    taskId)
            }
            taskViewModel.updateTask(task)
            parentFragmentManager.popBackStack()
        }
    }
}