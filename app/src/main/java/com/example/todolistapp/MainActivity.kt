package com.example.todolistapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTask: EditText
    private lateinit var spinnerPriority: Spinner
    private lateinit var btnAddTask: Button
    private lateinit var lvTasks: ListView

    private val taskList = mutableListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTask = findViewById(R.id.etTask)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        btnAddTask = findViewById(R.id.btnAddTask)
        lvTasks = findViewById(R.id.lvTasks)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        lvTasks.adapter = arrayAdapter

        btnAddTask.setOnClickListener {
            val task = etTask.text.toString()
            val priority = spinnerPriority.selectedItem.toString()

            if (task.isNotEmpty()) {
                taskList.add("Task: $task - Priority: $priority")
                arrayAdapter.notifyDataSetChanged()
                etTask.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        spinnerPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedPriority = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected: $selectedPriority", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}
