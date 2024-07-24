package com.example.java_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter todoAdapter;
    private RecyclerView rvTodoItems;
    private android.widget.Button btnAddTodo;
    private android.widget.EditText etTodoTitle;
    private android.widget.Button btnDeleteDoneTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTodoItems = findViewById(R.id.rvTodoItems);
        btnAddTodo = findViewById(R.id.btnAddTodo);
        etTodoTitle = findViewById(R.id.etTodoTitle);
        btnDeleteDoneTodos = findViewById(R.id.btnDeleteDoneTodos);

        todoAdapter = new TodoAdapter(new ArrayList<>());

        rvTodoItems.setAdapter(todoAdapter);
        rvTodoItems.setLayoutManager(new LinearLayoutManager(this));

        btnAddTodo.setOnClickListener(view -> {
            String todoTitle = etTodoTitle.getText().toString();
            if (!todoTitle.isEmpty()) {
                Todo todo = new Todo(todoTitle);
                todoAdapter.addTodo(todo);
                etTodoTitle.setText("");
            }
        });

        btnDeleteDoneTodos.setOnClickListener(view -> {
            todoAdapter.deleteDoneTodos();
        });
    }
}

