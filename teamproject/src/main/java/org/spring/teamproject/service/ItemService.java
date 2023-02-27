package org.spring.teamproject.service;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.ItemDto;
import org.spring.teamproject.entity.FileEntity;
import org.spring.teamproject.entity.ItemEntity;
import org.spring.teamproject.entity.MemberEntity;
import org.spring.teamproject.repository.FileRepository;
import org.spring.teamproject.repository.ItemRepository;
//import org.spring.teamproject.repository.MemberRepository;
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


            MultipartFile itemFile = itemDto.getItemFile(); //업로드 할 파일 get
            String fileName = itemFile.getOriginalFilename(); //업로드 할 파일명 get

//            UUID uuid = UUID.randomUUID(); //파일명 앞에 들어갈 랜덤코드 (중복을위
            String newFileName =  fileName; //파일명 커스텀 ex) DSAhd + _ + img_jpg

            String filepath = "C:/projectfile/"+newFileName; //상품 추가할 때 실제로 넣을 파일들 경로 (config package -> UploadPath 참고)
            itemFile.transferTo(new File(filepath)); //커스텀 파일 생성 (exception 해줘야함)



            ItemEntity itemEntity = ItemEntity.toItemEntity(itemDto);
            Long itemNo = itemRepository.save(itemEntity).getNo(); //상품등록하고 등록 한 pk get

            Optional<ItemEntity> itemIdSearch = itemRepository.findByNo(itemNo); //save한 pk찾아서
            ItemEntity itemId = itemIdSearch.get(); //가져오고

            FileEntity fileEntity = FileEntity.fileUpload(itemId, fileName, newFileName);
            fileRepository.save(fileEntity);//파일 저장

        }


    public List<ItemDto> itemList() {
        List<ItemDto> itemDtoList=new ArrayList<>();

        List<ItemEntity> itemEntityList=itemRepository.findAll();

        for(ItemEntity itemEntity:itemEntityList){
            itemDtoList.add(ItemDto.toItemDto(itemEntity));
        }

        return itemDtoList;
    }
}
