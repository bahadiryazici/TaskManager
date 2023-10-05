package com.example.taskmanager.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanager.model.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao()
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE id LIKE :id ")
    fun getById(id: Int) : Task

    @Query("SELECT * FROM tasks ORDER BY priority ASC")
    suspend fun getAll() : List<Task>

    @Query("SELECT * from tasks where statement LIKE :statement ORDER BY priority ASC")
    suspend fun getStatement(statement : String) : List<Task>

    //SELECT  c.CategoryName, COUNT(t.category) AS "tASK COUNT"  from tasks as t,categories as c Where t.category =c.CategoryName  GROUP BY t.category;
    @Query("SELECT  id,personCount,MIN(date) as \"date\",taskName,category,COUNT(category) AS \"count\"  from tasks  GROUP BY category ORDER BY date ")
    suspend fun getcategory() : List<Task>

    @Query("SELECT * FROM tasks\n" +
            "ORDER BY \n" +
            "  CASE \n" +
            "    WHEN length(date) = 12 THEN substr(date, 9, 7) || '-' || substr(date, 3, 4) || '-' || substr(date, 6, 4)\n" +
            "    WHEN length(date) = 11 AND substr(date, 5, 3) = '-' THEN substr(date, 8, 6) || '-0' || substr(date, 3, 3) || '-' || substr(date, 5, 4)\n" +
            "    WHEN length(date) = 11 AND substr(date, 4, 3) = '-' THEN substr(date, 7, 6) || '-0' || substr(date, 3, 3) || '-' || substr(date, 4, 4)\n" +
            "    WHEN length(date) = 10 AND substr(date, 4, 3) = '-' THEN substr(date, 7, 6) || '-0' || substr(date, 3, 3) || '-0' || substr(date, 5, 3)\n" +
            "    ELSE '' \n" +
            "  END ASC\n ")
    suspend fun getDate() : List<Task>

    @Insert
    fun insert(task : Task)

    @Update
    fun update(task : Task)

}

