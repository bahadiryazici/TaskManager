package com.example.taskmanager.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanager.model.Task

@Database(entities = [Task::class] , version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context) : TaskDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "Tasks"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}


