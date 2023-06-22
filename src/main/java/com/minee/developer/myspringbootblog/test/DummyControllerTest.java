package com.minee.developer.myspringbootblog.test;

import com.minee.developer.myspringbootblog.model.User;
import com.minee.developer.myspringbootblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입
    private UserRepository userRepository;
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username " + user.getUsername());
        System.out.println("password " + user.getPassword());
        System.out.println("email " + user.getEmail());

        userRepository.save(user);

        return "회원가입이 왼료되었습니다.";
    }
}
