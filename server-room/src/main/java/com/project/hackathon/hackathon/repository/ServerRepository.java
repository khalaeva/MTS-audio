package com.project.hackathon.hackathon.repository;

import com.project.hackathon.hackathon.entity.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServerRepository extends JpaRepository<ServerEntity, UUID> {
    boolean existsByIdAndOwnerUser(UUID id, UUID owner);
}
