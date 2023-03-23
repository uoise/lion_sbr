package com.ll.sbr.user.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registerDate;
}
