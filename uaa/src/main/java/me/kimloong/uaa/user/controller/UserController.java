package me.kimloong.uaa.user.controller;

import me.kimloong.uaa.user.model.User;
import me.kimloong.uaa.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * User Controller
 *
 * @author KimLoong
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void register(@RequestBody User user){
        userService.register(user);
    }
}
