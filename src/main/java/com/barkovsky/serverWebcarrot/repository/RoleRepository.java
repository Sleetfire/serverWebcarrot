package com.barkovsky.serverWebcarrot.repository;

import com.barkovsky.serverWebcarrot.model.ERole;
import com.barkovsky.serverWebcarrot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
