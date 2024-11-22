package com.example.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    private static final String  db_name = "TodoApp.db";
    private static final int db_version = 1;
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_STATUS = "status";

    private final static String CREATE_TABLE_TASKS = "CREATE TABLE " + TABLE_TASKS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TASK + " TEXT, " +
            COLUMN_STATUS + " INTEGER" + ")";

    public TaskDatabaseHelper(Context context){
        super(context,db_name,null,db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
    }

    public long addTask(String task , int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK,task);
        values.put(COLUMN_STATUS,status);

        return db.insert(TABLE_TASKS,null,values);
    }

    public List<Task> allTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TASKS,null,null,null,null,null,null);
        List<Task> taskList = new ArrayList<>();
        if(cursor!=null && cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String taskName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK));
                int status = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STATUS));
                taskList.add(new Task(id, taskName, status)); // Assuming you have a Task constructor with these fields
            } while (cursor.moveToNext());
            cursor.close();
        }
        return taskList;
    }

    public int deleteTask(int taskId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TASKS,COLUMN_ID + " =?", new String[]{String.valueOf(taskId)});
    }

    public int updateTask(int taskId , int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUS,status);
        return db.update(TABLE_TASKS,values,COLUMN_ID + " =?",new String[]{String.valueOf(taskId)});
    }
}
