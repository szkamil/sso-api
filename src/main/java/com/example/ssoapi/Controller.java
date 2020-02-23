package com.example.ssoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Handler handler;
    @ResponseBody
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String getResource( @RequestParam("id") String id) throws Exception {
        return handler.getHandler( id );

    }
}
