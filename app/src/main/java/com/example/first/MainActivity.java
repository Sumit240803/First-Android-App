package com.example.first;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskDatabaseHelper dbHelper;
    private EditText editText;
    private List<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new TaskDatabaseHelper(this);
        editText = findViewById(R.id.editTextTask);
        Button button = findViewById(R.id.button);
        ListView listView = findViewById(R.id.listView);
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this,taskList , dbHelper);
        listView.setAdapter(taskAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText  = editText.getText().toString();
                if(!taskText.isEmpty()){
                    dbHelper.addTask(taskText,0);
                    editText.setText("");
                    loadTasks();
                }
            }
        });
        loadTasks();

    }
    private void loadTasks(){
        taskList.clear();
        taskList.addAll(dbHelper.allTasks());
        taskAdapter.notifyDataSetChanged();
    }

}