package org.spring.teamproject.dto;

import lombok.*;
import org.spring.teamproject.entity.CartItemEntity;
import org.spring.teamproject.entity.ItemEntity;
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
    private String duration; //불확실
    private String producer;
    private String price; //$
    private int bpm;
    private String genre; //장르
    private MemberEntity member;
    private CartItemEntity cartItem;

    private MultipartFile itemFile; //저장되는 객체

    private String fileName;
    private String newFileName;
    private int attachFile;//파일유무


    public static ItemDto toItemDto(ItemEntity itemEntity) {

        ItemDto itemDto = new ItemDto();
        itemDto.setNo(itemEntity.getNo());
        itemDto.setTitle(itemEntity.getTitle());
        itemDto.setDuration(itemEntity.getDuration());
        itemDto.setBpm(itemEntity.getBpm());
        itemDto.setProducer(itemEntity.getProducer());
        itemDto.setGenre(itemEntity.getGenre());
        itemDto.setPrice(itemEntity.getPrice());
        if (itemEntity.getAttachFile() == 0) {
            itemDto.setAttachFile(itemEntity.getAttachFile()); //파일이 없을 경우0
        } else {
            itemDto.setAttachFile(itemEntity.getAttachFile()); //파일이 있을 경우1
            //파일이 있을 경우
            // 원래 파일이름 가져온다. ->  DB에서 파일의 원래이름 가져오는 방법
            itemDto.setFileName(itemEntity.getFileEntityList().get(0).getFileName());
            // 새파일이름(DB저장이름) 가져온다. -> DB에서 파일의 새파일이름 가져오는 방법
            itemDto.setNewFileName(itemEntity.getFileEntityList().get(0).getNewFileName());

        }
        return itemDto;
    }


}
