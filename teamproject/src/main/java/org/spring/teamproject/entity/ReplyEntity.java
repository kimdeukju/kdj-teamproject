package org.spring.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reply")
public class ReplyEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_no")
    private Long no;

    @Column(nullable = false)
    private String writer;
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private BoardEntity board;

    @ManyToOne()
    @JoinColumn(name = "member_no")
    private MemberEntity reply_member;


}
