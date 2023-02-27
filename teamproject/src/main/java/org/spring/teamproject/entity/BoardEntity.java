package org.spring.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long no;

    @Column(nullable = false)
    private String writer;
    private String title;
    private String content;
    private String youtubeLink;
    private int likes;
    private int views;

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity board_member;

}
