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
@Table(name = "item")
public class ItemEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long no;

    @Column(nullable = false)
    private String itemName; //track title
    private int duration; //불확실
    private String producer;
    private int price; //$
    private int bpm;
    private String genre; //장르

    //관리자가 아이템 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private MemberEntity item_member;

    //선택사항

    @OneToMany(mappedBy = "item",cascade = CascadeType.REMOVE)
    private List<CartItemEntity> itemEntityList = new ArrayList<>();





}
