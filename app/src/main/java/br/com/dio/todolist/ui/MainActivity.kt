package br.com.dio.todolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.com.dio.todolist.database.AppDatabase
import br.com.dio.todolist.database.ToDoEntity
import br.com.dio.todolist.databinding.ActivityMainBinding
import br.com.dio.todolist.datasource.TaskDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }
    lateinit var todoList : LiveData<List<ToDoEntity>>


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter
        updateList()

        insertListeners()
        //DATA STORE
        val appDatabase :AppDatabase = Room.databaseBuilder(this,AppDatabase::class.java,"mydb")
            .allowMainThreadQueries()
            .build()//ROOM
    }


    private fun insertListeners() {
        binding.fab.setOnClickListener {
            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }


        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

        adapter.listenerDelete = {
            TaskDataSource.deleteTask(it)
            updateList()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) updateList()
    }

    private fun updateList() {
        val list = TaskDataSource.getList()
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE
        else View.GONE

        adapter.submitList(list)
    }


    companion object {
        private const val CREATE_NEW_TASK = 1000
    }

}