package com.challenger.eurekaclientlobby.repository;

import com.challenger.eurekaclientlobby.entity.RoomMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RoomMemberRepository extends JpaRepository<RoomMemberEntity, UUID> {
    boolean existsByServerIdAndRoomId(UUID serverId, UUID roomId);
    Optional<RoomMemberEntity> findByUserId(UUID userId);

    @Query("""
    select rm from RoomMemberEntity rm
    where rm.serverId = :serverId and rm.roomId = :roomId
    order by rand() limit 1
    """)
    Optional<RoomMemberEntity> getRandomUser(
            @Param("serverId") UUID serverId,
            @Param("roomId") UUID roomId
    );
}
