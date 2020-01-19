package com.sy.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: shenyang
 * @Date: 2020/1/16 16:33
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping("/1")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
