package com.ee4216.as2.MovieAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TodoListDao {
    private JdbcTemplate jdbcTemplate;

    public TodoListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TodoList> getAllTodoLists() {
        return jdbcTemplate.query("SELECT * FROM todo_list", new RowMapper<TodoList>() {

            @Override
            public TodoList mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoList(rs.getInt("id"), rs.getString("owner"), rs.getString("title"),
                        rs.getString("description"), rs.getString("stage"));
            }

        });
    }

    public List<TodoList> getTodoListByOwnerId(String ownerId) {
        return jdbcTemplate.query("SELECT * FROM todo_list WHERE owner = ?", new RowMapper<TodoList>() {

            @Override
            public TodoList mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoList(rs.getInt("id"), rs.getString("owner"), rs.getString("title"),
                        rs.getString("description"), rs.getString("stage"));
            }

        }, ownerId);
    }

    public void addTodoList(TodoList todoList) {
        jdbcTemplate.update("INSERT INTO todo_list (owner, title, description, stage) VALUES (?, ?, ?, ?)",
                todoList.getOwner(), todoList.getTitle(), todoList.getDescription(), todoList.getStage());
    }

    public void updateTodoList(TodoList todoList) {
        jdbcTemplate.update("UPDATE todo_list SET owner = ?, title = ?, description = ?, stage = ? WHERE id = ?",
                todoList.getOwner(), todoList.getTitle(), todoList.getDescription(), todoList.getStage(),
                todoList.getId());
    }

    public void deleteTodoList(int id) {
        jdbcTemplate.update("DELETE FROM todo_list WHERE id = ?", id);
    }

    public void getTodoListByOwnerIdandStage(TodoList todoList) {
        jdbcTemplate.update("SELECT * FROM todo_list WHERE owner = ? AND stage = ?", todoList.getOwner(),
                todoList.getStage());
    }

}
