package br.com.dio.todolist.extensions

import br.com.dio.todolist.database.TaskEntity
import br.com.dio.todolist.model.Task

fun TaskEntity.toModel(): Task {
    return Task(id = id, title = todo, hour = hour, date = date)
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(id = id, todo = title, date = date, hour = hour)
}