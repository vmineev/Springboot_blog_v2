package com.minee.developer.myspringbootblog.controller.api;

import com.minee.developer.myspringbootblog.dto.ResponseDto;
import com.minee.developer.myspringbootblog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController 호출 됨");
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }
}
