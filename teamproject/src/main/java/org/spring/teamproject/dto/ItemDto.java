package org.spring.teamproject.dto;

import lombok.*;
import org.spring.teamproject.entity.CartItemEntity;
import org.spring.teamproject.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {

    private Long no;
    private String title; //track title
    private int duration; //불확실
    private String producer;
    private int price; //$
    private int bpm;
    private String genre; //장르
    private MemberEntity member;
    private CartItemEntity cartItem;

    private MultipartFile itemFile; //저장되는 객체

    private String fileName;
    private String newFileName;


}
