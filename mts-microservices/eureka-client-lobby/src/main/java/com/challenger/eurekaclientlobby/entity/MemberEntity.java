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
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;
    private UUID serverId;

    @CreatedDate
    @Builder.Default
    private Date joined = new Date();

}
