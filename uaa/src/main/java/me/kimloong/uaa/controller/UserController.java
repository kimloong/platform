package me.kimloong.uaa.controller;

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

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void register(){

    }
}
