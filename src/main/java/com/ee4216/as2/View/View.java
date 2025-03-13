package com.ee4216.as2.View;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class View {

    Logger logger = org.slf4j.LoggerFactory.getLogger(View.class);

    @RequestMapping("/login")
    public String login() {
        logger.info("Login endpoint accessed");
        return "login";
    }

    @RequestMapping("/registration")
    public String register() {
        return "registration";
    }

    // if user is not logged in, redirect to login page
    @RequestMapping("/home")
    public ModelAndView home(HttpSession session, Model model, @RequestParam String uuid) {
        logger.info("uuid: " + uuid);
        if (session.getAttribute(uuid) == null) {
            return new ModelAndView("redirect:/api/login");
        } else {
            model.addAttribute("userid", session.getAttribute(uuid));
            return new ModelAndView("home");
        }
    }
}
