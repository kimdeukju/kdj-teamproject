package org.spring.teamproject.repository;

import org.spring.teamproject.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
}
