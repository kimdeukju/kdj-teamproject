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
@Table(name = "file")
public class FileEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_no")
    private Long no;

    private String fileName; //기존 파일명
    private String newFileName;  //uuid random


    @ManyToOne
    @JoinColumn(name = "item_no")
    private ItemEntity file_item;

    public static FileEntity fileUpload(ItemEntity itemEntity, String fileName, String newFileName){

        FileEntity fileEntity = new FileEntity();

        fileEntity.setFile_item(itemEntity);
        fileEntity.setFileName(fileName);
        fileEntity.setNewFileName(newFileName);

        return fileEntity;


    }


}
