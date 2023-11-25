package com.challenger.eurekaclientlobby.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "room_member")
@NoArgsConstructor
@AllArgsConstructor
public class RoomMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;
    private UUID serverId;
    private UUID roomId;

    private RoomMemberStatus status;

}
