package com.barkovsky.serverWebcarrot.repository;


import com.barkovsky.serverWebcarrot.model.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {

//    Page<Items> findByCollectionsId(Long collectionsId, Pageable pageable);
//    Optional<Items> findByCollectionsIdAndItemsId(Long id, Long collectionsId);
    List<Items> findByTitleContaining (String title);
    List<Items> findByPublished (boolean published);
}
