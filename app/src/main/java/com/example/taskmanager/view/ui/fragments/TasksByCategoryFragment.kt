package com.example.taskmanager.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.view.adapter.TaskCategoryAdapter
import com.example.taskmanager.databinding.FragmentTasksByCategoryBinding
import com.example.taskmanager.roomdb.TaskDao
import com.example.taskmanager.viewmodel.TaskViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class TasksByCategoryFragment : Fragment() {

    private var _binding : FragmentTasksByCategoryBinding ?= null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Inflate the layout for this fragment
        _binding = FragmentTasksByCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]


        getDatabaseByCategory()

        binding.imageView2.setOnClickListener  {
            parentFragmentManager.popBackStack()
        }
    }

    private fun getDatabaseByCategory(){
      taskViewModel.taskLiveDataByCategory.observe(viewLifecycleOwner) {
          it?.let {
              binding.recyclerTaskCategory.layoutManager =
                  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
              val taskAdapter = TaskCategoryAdapter(it)
              binding.recyclerTaskCategory.adapter = taskAdapter
              if (it.isEmpty()) {
                  binding.emptyTextView.visibility = View.VISIBLE
                  binding.recyclerTaskCategory.visibility = View.INVISIBLE
              } else {
                  binding.emptyTextView.visibility = View.GONE
                  binding.recyclerTaskCategory.visibility = View.VISIBLE
              }
          }
      }
        taskViewModel.getByCategory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}