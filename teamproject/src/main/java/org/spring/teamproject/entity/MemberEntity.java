package org.spring.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.teamproject.role.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    @Column(nullable = false)
    private String email;
    private String password;
    private String Address;
    private String userName;
    private String phone;

    @Enumerated
    private Role role;

    //관리자
    @OneToMany(mappedBy = "item_member",cascade = CascadeType.REMOVE)
    private List<ItemEntity> itemEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "board_member",cascade = CascadeType.REMOVE)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "reply_member",cascade = CascadeType.REMOVE)
    private List<ReplyEntity> replyEntityList= new ArrayList<>();
    //@OneToOne
    //text
}
