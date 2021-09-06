package br.com.dio.todolist.database

import androidx.room.*
import androidx.lifecycle.LiveData

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<TaskEntity>>

    @Query("select * from task where id = :id")
    fun findById(id: Int): TaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}
