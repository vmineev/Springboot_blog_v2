package com.minee.developer.myspringbootblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //PK

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; //섬머노트 라이브러리 이용, <html> 태그가 섞여서 디자인 됨

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) //Many = Board,One = User , fetch 데이터를 바로 가져옴
    @JoinColumn(name = "userId")
    private User user; //FK

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // PK-FK 관계가 아님
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createdDate;
}
