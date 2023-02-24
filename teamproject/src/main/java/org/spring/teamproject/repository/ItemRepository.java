package org.spring.teamproject.repository;


import org.spring.teamproject.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {

    Optional<ItemEntity> findByNo(Long itemNo);
}
