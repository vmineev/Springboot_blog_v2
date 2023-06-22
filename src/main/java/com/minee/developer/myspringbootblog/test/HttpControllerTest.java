package com.minee.developer.myspringbootblog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member member = Member.builder().username("hee").password("1234").email("hee@abc.com").build();
        System.out.println(TAG + "getter : " + member.getUsername());
        member.setUsername("min");
        System.out.println(TAG + "setter : " + member.getUsername());
        return "lombok test 완료";
    }

    //인터넷 브라우저 요청은 get 만 가능
    @GetMapping("/http/get")
    public String getTest(Member member){
        return "get 요청: " + member.getId() + member.getUsername();
    }


    //스프링 부트의 MessageConvertor 가 json 데이터를 파싱해 Object 와 매핑
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청: " + m.getId()+m.getUsername()+m.getPassword()+m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
