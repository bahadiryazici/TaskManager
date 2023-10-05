package com.example.taskmanager.util


import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.taskmanager.R
import com.example.taskmanager.model.Participant
import com.example.taskmanager.view.adapter.ParticipantAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

class Util {

    fun addParticipant(participantList : ArrayList<Participant>, adapter : ParticipantAdapter){
        val participant = Participant(R.drawable.baseline_person_24)
        participantList.add(0,participant)
        adapter.notifyItemInserted(participantList.size - 1)
    }
    fun categorySpinner(categoryList: ArrayList<String>, context: Context): ArrayAdapter<String> {
        categoryList.add("Design")
        categoryList.add("Document")
        categoryList.add("Marketing")
        return ArrayAdapter(context, R.layout.spinner_style, R.id.spinnerText, categoryList)
    }
    fun alertDialog(categoryList : ArrayList<String>,context: Context, addCategoryView : View, adapter:ArrayAdapter<String>){
        val alertDialogBuilder = MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
        alertDialogBuilder.setView(addCategoryView)
        alertDialogBuilder.setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                val userInput = addCategoryView.findViewById<EditText>(R.id.editTextDialogUserInput)
                if(userInput?.text.toString() == ""){
                    Toast.makeText(context,"Please fill the field.", Toast.LENGTH_LONG).show()
                }else{
                    categoryList.add(userInput?.text.toString())
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.cancel()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
     fun getTodayDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        month += 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return makeDateString( day, month, year )
    }
     private fun makeDateString(day: Int, month: Int, year: Int): String {
        return getMonthFormat(month) + " " + day + " " + year
    }
    private fun getMonthFormat(month: Int): String {
        when (month) {
            1 -> return " Jan"
            2 -> return " Feb"
            3 -> return " Mar"
            4 -> return " Apr"
            5 -> return " May"
            6 -> return " Jun"
            7 -> return " Jul"
            8 -> return " Aug"
            9 -> return " Sep"
            10 -> return " Oct"
            11 -> return " Nov"
            12 -> return " Dec"
            else -> return "Dec"
        }
    }
     fun initDatePicker( context: Context, button: Button) : DatePickerDialog {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

            val month = month + 1
            val date = makeDateString(day,month,year)
            button.text = date
        }
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val style = R.style.MyTimePickerDialogTheme
        val datePickerDialog = DatePickerDialog(context,style,dateSetListener,year,month,day)
        datePickerDialog.datePicker.minDate = Date().time - 10000
        return datePickerDialog
    }

}