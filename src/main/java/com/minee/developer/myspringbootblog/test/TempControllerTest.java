package com.minee.developer.myspringbootblog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/jsp")
    public String tempJsp(){
        return "test";
    }
}
