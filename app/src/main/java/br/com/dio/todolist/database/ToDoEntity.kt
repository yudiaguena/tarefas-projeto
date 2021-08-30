package br.com.dio.todolist.database

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "Tasks")
data class ToDoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "todo")
    @NonNull
    var Todo: String,

    @ColumnInfo(name = "date")
    @NonNull
    var date: String,

    @ColumnInfo(name = "hour")
    @NonNull
    var hour: String
)
