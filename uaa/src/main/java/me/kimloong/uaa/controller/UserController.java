package me.kimloong.uaa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Zhang JinLong(150429) on 2017-08-23.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String demo() {
        return "Hello World";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String home(Principal principal) {
        return "Hello World";
    }
}
