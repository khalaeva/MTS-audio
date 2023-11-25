package com.challenger.eurekaclientlobby.repository;

import com.challenger.eurekaclientlobby.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {
    boolean existsByUserIdAndServerId(UUID userId, UUID serverId);
}
