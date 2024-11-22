package com.example.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> taskList;

    private TaskDatabaseHelper databaseHelper;

    public TaskAdapter(@NonNull Context context , List<Task> taskList , TaskDatabaseHelper databaseHelper){
        super(context,0,taskList);
        this.context = context;
        this.taskList = taskList;
        this.databaseHelper = databaseHelper;
    }
    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.text_view,parent ,false);
        }
        Task task = taskList.get(position);
        TextView textView = convertView.findViewById(R.id.textView4);
        textView.setText(task.getTask());
        Button deleteButton = convertView.findViewById(R.id.button5);
        deleteButton.setOnClickListener(
                v->{
                    databaseHelper.deleteTask(task.getId());
                    taskList.remove(position);
                    notifyDataSetChanged();
                }
        );
        return convertView;
    }

}
