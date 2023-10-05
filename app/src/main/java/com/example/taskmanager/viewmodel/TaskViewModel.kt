package com.example.taskmanager.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.model.Task
import com.example.taskmanager.roomdb.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDatabase(getApplication()).taskDao()

    val taskLiveData = MutableLiveData<List<Task>>()
    val taskLiveDataByStatement = MutableLiveData<List<Task>>()
    val taskLiveDataByDate = MutableLiveData<List<Task>>()
    val taskLiveDataByCategory = MutableLiveData<List<Task>>()
    val taskLiveDataByID = MutableLiveData<Task>()


    fun getDataFromRoom() {
        viewModelScope.launch {
            val task = taskDao.getAll()
            taskLiveData.value = task
        }
    }
    fun getDataByID(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val taskByID = taskDao.getById(id)
            taskLiveDataByID.postValue(taskByID)
        }
    }
    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }
    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.update(task)
        }
    }
    fun getStatement(statement: String) {
        viewModelScope.launch {
            val taskByStatement = taskDao.getStatement(statement)
            taskLiveDataByStatement.value = taskByStatement
        }
    }
    fun getByDate() {
        viewModelScope.launch {
            val taskByDate = taskDao.getDate()
            taskLiveDataByDate.value = taskByDate
        }
    }
    fun getByCategory() {
        viewModelScope.launch {
            val taskByCategory = taskDao.getcategory()
            taskLiveDataByCategory.value = taskByCategory
        }
    }
}

