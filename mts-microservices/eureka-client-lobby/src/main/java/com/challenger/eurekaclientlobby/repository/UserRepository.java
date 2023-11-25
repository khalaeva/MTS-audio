package com.challenger.eurekaclientlobby.repository;

import com.challenger.eurekaclientlobby.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByIdAndCurrentRoomIsNotNull(UUID id);
}
