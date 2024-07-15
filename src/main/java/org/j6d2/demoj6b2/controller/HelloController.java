package org.j6d2.demoj6b2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello.th")
    public String hello(Model model) {
        model.addAttribute("msg", "Hello, Java 6!");
        return "hello";
    }
}
