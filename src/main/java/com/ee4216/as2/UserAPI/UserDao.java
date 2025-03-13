package com.ee4216.as2.UserAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getString("userid"), rs.getString("password"), rs.getString("name"));
            }
        });
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users (userid, password, name) VALUES (?, ?, ?)", user.getUserid(),
                user.getPassword(), user.getName());
    }

    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET password = ?, name = ? WHERE userid = ?", user.getPassword(),
                user.getName(), user.getUserid());
    }

    public User getUser(String userid) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE userid = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getString("userid"), rs.getString("password"), rs.getString("name"));
            }
        }, userid);
    }

}