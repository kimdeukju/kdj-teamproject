package org.spring.teamproject.service;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.ItemDto;
import org.spring.teamproject.entity.FileEntity;
import org.spring.teamproject.entity.ItemEntity;
import org.spring.teamproject.repository.FileRepository;
import org.spring.teamproject.repository.ItemRepository;
//import org.spring.teamproject.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileRepository fileRepository;
//    private final MemberRepository memberRepository;


    @Transactional
    public void itemInsert(ItemDto itemDto) throws IOException {

        if(itemDto.getItemFile().isEmpty()){
            ItemEntity itemEntity=ItemEntity.toItemEntity(itemDto);
            itemRepository.save(itemEntity);
        }else{
            MultipartFile itemFile = itemDto.getItemFile(); // Dto에서 파일을 가져온다.
            String fileName = itemFile.getOriginalFilename(); //원본파일이름

            UUID uuid = UUID.randomUUID(); //파일명 앞에 들어갈 랜덤코드
            String newFileName =  uuid + "_" +fileName; //파일명 커스텀 ex) DSAhd + _ + img_jpg

            String filepath = "C:/projectfile/"+newFileName; //상품 추가할 때 실제로 넣을 파일들 경로 (config package -> UploadPath 참고)
            itemFile.transferTo(new File(filepath));// 저장  ,예외처리 -> 경로에 파일 저장

            ItemEntity itemEntity=ItemEntity.toItemFileEntity(itemDto);//item에 저장 한후 파일저장
            Long itemNo=itemRepository.save(itemEntity).getNo();//저장 실행-> item의 no를 가져온다.

            Optional<ItemEntity> itemEntity1=itemRepository.findByNo(itemNo);// no에 해당하는 item
            ItemEntity itemEntity2=itemEntity1.get();

            //item no, 원래 파일이름, 새파일이름
            FileEntity fileEntity=FileEntity.fileUpload(itemEntity2,fileName,  newFileName);
            fileRepository.save(fileEntity);//파일 저장

        }
    }
    public List<ItemDto> itemList() {
        List<ItemDto> itemDtoList=new ArrayList<>();

        List<ItemEntity> itemEntityList=itemRepository.findAll();

        for(ItemEntity itemEntity:itemEntityList){
            itemDtoList.add(ItemDto.toItemDto(itemEntity));
        }
        return itemDtoList;
    }

    public Page<ItemDto> itemPage(Pageable pageable) {
        Page<ItemEntity> itemEntities=itemRepository.findAll(pageable);

        Page<ItemDto> itemDtoPage=itemEntities.map(ItemDto::toItemDto);

        return itemDtoPage;
    }

    public ItemDto trackDetail(long no) {
        Optional<ItemEntity> itemEntity=itemRepository.findByNo(no);

        if(itemEntity.isPresent()){
            ItemDto itemDto=ItemDto.toItemDto(itemEntity.get());

            return itemDto;
        }else{
            return null;
        }
    }
}

































