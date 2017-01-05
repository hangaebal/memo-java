package com.hangaebal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by abc on 2017-01-05.
 */
@Controller
public class RootController {
    @RequestMapping("/")
    public @ResponseBody String index() {
        return "hello";
    }
}
