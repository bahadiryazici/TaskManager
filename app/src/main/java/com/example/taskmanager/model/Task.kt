package com.example.taskmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class Task(

    @ColumnInfo(name="taskName")
    var taskName:String ?= null,

    @ColumnInfo(name ="date")
    var date : String ?= null,

    @ColumnInfo(name="startTime")
    var startTime : String?= null,

    @ColumnInfo(name="endTime")
    var endTime : String?= null,

    @ColumnInfo(name ="category")
    var category : String?= null,

    @ColumnInfo(name ="priority")
    var priority : String?= null,

    @ColumnInfo(name = "personCount")
    var personCount : Int?= null,

    @ColumnInfo(name="description")
    var description : String?= null,

    @ColumnInfo(name="count")
    var count : String?= null,

    @ColumnInfo(name = "statement")
    var statement : String ?= null,

    @PrimaryKey(autoGenerate = true)
    var id : Int
    ){}
/*
@Entity(tableName = "tasks")
class Task(
    @ColumnInfo(name="taskName")
    var taskName:String ?= null,
    @ColumnInfo(name ="date")
    var date : String ?= null,
    @ColumnInfo(name="startTime")
    var startTime : String?= null,
    @ColumnInfo(name="endTime")
    var endTime : String?= null,
    @ColumnInfo(name ="priority")
    var priority : String?= null,
    @ColumnInfo(name = "personCount")
    var personCount : Int?= null,
    @ColumnInfo(name="description")
    var description : String?= null,
    @PrimaryKey(autoGenerate = true)
    var id : Int
){}*/