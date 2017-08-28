package me.kimloong.resource.sample.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by KimLoong on 17-8-27.
 */
@RestController
@RequestMapping("/resource_samples/demos")
public class DemoController {

    @RequestMapping(method = RequestMethod.GET)
    public Principal get(Principal principal) {
        return principal;
    }
}
