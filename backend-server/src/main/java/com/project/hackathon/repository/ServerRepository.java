package com.project.hackathon.repository;

import com.project.hackathon.entity.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServerRepository extends JpaRepository<ServerEntity, UUID> {
    boolean existsByIdAndOwnerUser(UUID id, UUID owner);
}
