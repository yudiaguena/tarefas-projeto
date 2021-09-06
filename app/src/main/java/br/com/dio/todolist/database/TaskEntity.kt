package br.com.dio.todolist.database

import androidx.annotation.NonNull
import androidx.room.*
import br.com.dio.todolist.model.Task

@Entity(tableName = "task")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "todo")
    @NonNull
    var todo: String,

    @ColumnInfo(name = "date")
    @NonNull
    var date: String,

    @ColumnInfo(name = "hour")
    @NonNull
    var hour: String
)
