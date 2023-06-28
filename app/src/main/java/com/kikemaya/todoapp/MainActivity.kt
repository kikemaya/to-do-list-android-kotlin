package com.kikemaya.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("Business Test", TaskCategory.Business),
        Task("Personal Test", TaskCategory.Personal),
        Task("Other Test", TaskCategory.Other)
    )

    //REFERENCE TO THE CATEGORIES RV
    private lateinit var rvCategories: RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var favAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        favAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val selectedId = rgCategories.checkedRadioButtonId
            val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
            val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                getString(R.string.todo_dialog_category_business) -> TaskCategory.Business
                getString(R.string.todo_dialog_category_personal) -> TaskCategory.Personal
                else -> TaskCategory.Other
            }

            tasks.add(Task(etTask.text.toString(), currentCategory))
            updateTasks()
            dialog.hide()
        }

        dialog.show()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)

        favAddTask = findViewById(R.id.favAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun updateTasks() {
        tasksAdapter.notifyDataSetChanged()
    }
}