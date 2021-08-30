package br.com.dio.todolist.database

import androidx.room.*
import androidx.lifecycle.LiveData

@Dao
interface ToDoDao {
    @Query("SELECT * FROM Tasks")
    fun getAll(): LiveData<List<ToDoEntity>>

    @Insert
    fun insertAll(vararg todo: ToDoEntity)

    @Delete
    fun delete(todo: ToDoEntity)
}
