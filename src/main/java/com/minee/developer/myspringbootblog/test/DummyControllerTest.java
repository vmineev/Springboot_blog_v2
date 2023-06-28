package com.minee.developer.myspringbootblog.test;

import com.minee.developer.myspringbootblog.model.RoleType;
import com.minee.developer.myspringbootblog.model.User;
import com.minee.developer.myspringbootblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){

        try {
            System.out.println("id = " + id);
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "해당 아이디를 찾을 수 없습니다.";
        }

        return "id : " + id + " 삭제가 완료되었습니다.";
    }

    //email, password 수정 하기
    @Transactional //함수 종료시 자동 commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User userRequest){
        System.out.println("id : " + id);
        System.out.println("email : " + userRequest.getEmail());
        System.out.println("password : " + userRequest.getPassword());

        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("수정에 실패했습니다."));
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        // @Transactional : save() 호출 하지 않아도 변경감지로 db update-> 더티 체킹
        //userRepository.save(user);

        return user;
    }

    //데이터 여러 건 조회
    @GetMapping("/dummy/users")
    public List<User> list(){
     return userRepository.findAll();
    }

    //한 페이지당 최근 생성된 아이디 기준으로 2건의 데이터를 받기, 쿼리 스트링으로 page=0 부터 조회 가능
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> userList = pagingUser.getContent();
        return userList;
    }

    //데이터 한 건 조회
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id ){

        //Optional 객체 : 값이 있으면 값을 return, null 일 경우 다른 처리
       User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
           //람다식으로 대체
           @Override
           public IllegalArgumentException get(){
               return new IllegalArgumentException("해당 ID ( " + id + " ) 를 찾을 수 없습니다.");
           }
       });

       // 스프리부트의 Message Converter 가 Jackson 라이브러리를 통해 자바 오브젝트를 json 으로 변환
       return user;
    }
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username " + user.getUsername());
        System.out.println("password " + user.getPassword());
        System.out.println("email " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 왼료되었습니다.";
    }
}
