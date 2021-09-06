package br.com.dio.todolist.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val todoDao: TaskDao) {

    suspend fun insert(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            todoDao.insertAll(task)
        }
    }

    fun getAll(): LiveData<List<TaskEntity>> {
        return todoDao.getAll()
    }

    suspend fun delete(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            todoDao.delete(task)
        }
    }
}