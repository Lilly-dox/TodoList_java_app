package com.example.java_app;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todos;

    public TodoAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTodoTitle;
        CheckBox cbDone;

        public TodoViewHolder(View itemView) {
            super(itemView);
            tvTodoTitle = itemView.findViewById(R.id.tvTodoTitle);
            cbDone = itemView.findViewById(R.id.cbDone);
        }
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo curTodo = todos.get(position);
        holder.tvTodoTitle.setText(curTodo.getTitle());
        holder.cbDone.setChecked(curTodo.isChecked());
        toggleStrikeThrough(holder.tvTodoTitle, curTodo.isChecked());

        holder.cbDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleStrikeThrough(holder.tvTodoTitle, isChecked);
            curTodo.setChecked(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        notifyItemInserted(todos.size() - 1);
    }

    public void deleteDoneTodos() {
        List<Todo> newTodos = new ArrayList<>();
        for (Todo todo : todos) {
            if (!todo.isChecked()) {
                newTodos.add(todo);
            }
        }
        todos = newTodos;
        notifyDataSetChanged();
    }

    private void toggleStrikeThrough(TextView tvTodoTitle, boolean isChecked) {
        if (isChecked) {
            tvTodoTitle.setPaintFlags(tvTodoTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            tvTodoTitle.setPaintFlags(tvTodoTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}