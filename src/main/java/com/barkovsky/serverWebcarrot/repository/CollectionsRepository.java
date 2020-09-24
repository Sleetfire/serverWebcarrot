package com.barkovsky.serverWebcarrot.repository;

import com.barkovsky.serverWebcarrot.model.Collections;
import com.barkovsky.serverWebcarrot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionsRepository extends JpaRepository<Collections, Long> {

//    Page<Collections> findByUserId(Long userId, Pageable pageable);
//    Optional<Collections> findByIdAndCollectionsId(Long id, Long userId);
    List<Collections> findByPublished (boolean published);
    List<Collections> findByTitleContaining (String title);

    List<Collections> findByUser (User user);

}
