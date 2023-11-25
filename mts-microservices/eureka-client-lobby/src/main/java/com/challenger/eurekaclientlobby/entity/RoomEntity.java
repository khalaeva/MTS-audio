package com.challenger.eurekaclientlobby.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID serverId;
    private String name;

    @OneToMany(mappedBy = "currentRoom")
    private List<UserEntity> listeners;

}
