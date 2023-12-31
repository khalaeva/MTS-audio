package com.challenger.eurekaclientlobby.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "server")
@NoArgsConstructor
@AllArgsConstructor
public class ServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private UUID ownerUser;

    @CreatedDate
    @Builder.Default
    private Date createdDate = new Date();


}
