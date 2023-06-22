package com.minee.developer.myspringbootblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String username; //아이디
    @Column(nullable = false,length = 100) //비밀번호 해쉬 암호화
    private String password;
    @Column(nullable = false,length = 50)
    private String email;

    @Enumerated(EnumType.STRING) //DB 에는 roletype 이 없기 때문에 알려준다
    private RoleType role; //enum 으로 도메인(데이터의 범위) 만들것

    @CreationTimestamp //시간 자동 입력
    private Timestamp createdDate;
}
