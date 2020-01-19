package com.sy.web;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */
@RestController
@RequestMapping(value = "/swagger")
public class SwaggerController {



    /**
     * @return
     */
    @ApiOperation(value = "Get all users", notes = "requires noting")
    @RequestMapping(method = RequestMethod.GET)
    public List<String> getUsers() {
        List<String> list = Arrays.asList("1", "2", "3");
        return list;
    }

    @ApiOperation(value = "Get user with id", notes = "requires the id of user")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Object getUserById(@PathVariable String name) {
        Map map = new HashMap();
        map.put(name, name);
        return map;
    }
}
