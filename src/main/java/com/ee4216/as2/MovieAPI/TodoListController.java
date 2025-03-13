package com.ee4216.as2.MovieAPI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ee4216.as2.MessageApi.Message;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/todolist")
public class TodoListController {

    private TodoListDao todoListDao;

    Logger logger = org.slf4j.LoggerFactory.getLogger(TodoListController.class);

    public TodoListController(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @GetMapping("/all")
    public List<TodoList> getAllTodoLists() {
        return todoListDao.getAllTodoLists();
    }

    // get todo list by user id
    @GetMapping(path = "/getByUserID/{uuid}", produces = "application/json")
    public List<TodoList> getTodoListByUserID(HttpSession session, @PathVariable String uuid) {
        String uuserid = (String) session.getAttribute(uuid);
        logger.info(uuserid);
        return todoListDao.getTodoListByOwnerId(uuserid);
    }

    // add todo list by user id
    @PostMapping(path = "/addTodoList/{uuid}")
    @ResponseBody
    public Message addTodoList(HttpSession session, @RequestBody TodoList todoList, @PathVariable String uuid) {
        String uuserid = (String) session.getAttribute(uuid);
        if (uuserid == null) {
            Message message = new Message("fail", null);
            return message;
        } else {
            todoListDao.addTodoList(todoList);
            Message message = new Message("add success", null);
            return message;
        }
    }

    // update todo list by user id
    @PostMapping(path = "/updateTodoList/{uuid}")
    @ResponseBody
    public Message updateTodoList(HttpSession session, @RequestBody TodoList todoList, @PathVariable String uuid) {
        String uuserid = (String) session.getAttribute(uuid);
        if (uuserid == null) {
            Message message = new Message("fail", null);
            return message;
        }
        todoListDao.updateTodoList(todoList);
        Message message = new Message("update success", null);
        return message;
    }

    // delete todo list by user id
    @DeleteMapping(path = "/deleteTodoList/{uuid}")
    @ResponseBody
    public Message deleteTodoList(HttpSession session, @RequestBody TodoList todoList, @PathVariable String uuid) {
        String uuserid = (String) session.getAttribute(uuid);
        if (uuserid == null) {
            Message message = new Message("fail", null);
            return message;
        }
        todoListDao.deleteTodoList(todoList.getId());
        Message message = new Message("delete success", null);
        return message;
    }

}
