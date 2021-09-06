package br.com.dio.todolist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.dio.todolist.database.AppDatabase
import br.com.dio.todolist.database.TaskEntity
import br.com.dio.todolist.database.TaskRepository
import br.com.dio.todolist.databinding.ActivityMainBinding
import br.com.dio.todolist.datasource.TaskDataSource
import br.com.dio.todolist.extensions.toEntity
import br.com.dio.todolist.extensions.toModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }
    private val repository by lazy {
        val database = AppDatabase.getDatabase(this)
        TaskRepository(database.todoDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter
        repository.getAll().observe(this, ::updateList)

        insertListeners()
    }


    private fun insertListeners() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivity(intent)
        }

        adapter.listenerDelete = {
            lifecycleScope.launch {
                repository.delete(it.toEntity())
            }
        }
    }

    private fun updateList(list: List<TaskEntity>) {
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE
        else View.GONE

        adapter.submitList(list.map { it.toModel() })
    }

}