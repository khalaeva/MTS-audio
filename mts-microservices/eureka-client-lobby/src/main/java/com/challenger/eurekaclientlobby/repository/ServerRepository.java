package com.challenger.eurekaclientlobby.repository;

import com.moscow.cup.entity.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServerRepository extends JpaRepository<ServerEntity, UUID> {
    boolean existsByIdAndOwnerUser(UUID id, UUID owner);
}
