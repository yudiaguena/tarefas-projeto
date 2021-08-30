package br.com.dio.todolist.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ToDoRepository(private val todoDao: ToDoDao) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todo: ToDoEntity) {
        todoDao.insertAll(todo)
    }
}