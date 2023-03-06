package org.spring.teamproject.repository;


import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.spring.teamproject.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {


    Optional<ItemEntity> findByNo(Long no);

    Optional<ItemEntity> deleteByNo(Long no);

    Page<ItemEntity> findByTitleContaining(String search, Pageable pageable);


//    Page<ItemEntity> findByBpmAndGenreAndPrice(int bpm,String genre,String price);

}
