package br.com.dio.todolist.database

import androidx.room.*
import androidx.lifecycle.LiveData

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<TaskEntity>>

    @Insert
    fun insertAll(vararg todo: TaskEntity)

    @Delete
    fun delete(todo: TaskEntity)
}
