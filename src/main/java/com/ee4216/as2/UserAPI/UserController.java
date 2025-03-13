package com.ee4216.as2.UserAPI;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ee4216.as2.MessageApi.Message;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private UserDao userDao;

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/all/{userid}")
    public List<User> getAllUsers(HttpSession session, String userid) {
        if (session.getAttribute(session.getId() + userid) == null)
            return null;
        else
            return userDao.getAllUsers();
    }

    // if login success, create the uuid session and return it as message object
    @PostMapping(path = "/userlogin")
    @ResponseBody
    public Message userLogin(HttpSession session, @RequestBody User user) {
        User checkUser = userDao.getUser(user.getUserid());
        if (checkUser != null && user.getPassword().equals(checkUser.getPassword())) {
            UUID uuid = UUID.randomUUID();
            Message message = new Message("success", uuid.toString());
            logger.info(message.getUUID());
            session.setAttribute(uuid.toString(), user.getUserid());
            session.setMaxInactiveInterval(60 * 5);
            logger.info("session uuid: " + session.getAttribute(uuid.toString()));
            return message;
        } else {
            Message message = new Message("fail", null);
            return message;
        }
    }

    // if add success, create the uuid session and return it as message object
    @PostMapping(path = "/addUser")
    @ResponseBody
    public Message addUser(HttpSession session, @RequestBody User user) {
        userDao.addUser(user);
        UUID uuid = UUID.randomUUID();
        session.setAttribute(uuid.toString(), user.getUserid());
        session.setMaxInactiveInterval(60 * 5);
        Message message = new Message("add success", uuid.toString());
        return message;
    }

    // if logout success, remove the uuid session and return success message
    @GetMapping(path = "/logout/{uuid}")
    @ResponseBody
    public Message logout(HttpSession session, @PathVariable String uuid) {
        session.removeAttribute(uuid);
        Message message = new Message("logout success", null);
        return message;
    }

}
