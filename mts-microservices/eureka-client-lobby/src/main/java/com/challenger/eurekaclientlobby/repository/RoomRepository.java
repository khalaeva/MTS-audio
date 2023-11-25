package com.challenger.eurekaclientlobby.repository;

import com.moscow.cup.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {

    @Query("select r.serverId from RoomEntity r where r.id = :id")
    Optional<UUID> getServerId(@Param("id") UUID id);

}
