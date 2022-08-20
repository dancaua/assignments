package com.abc.empapp.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AppUsers {
    @Id
    private Long userId;
    private String role;
    private String username;
    private String password;
}
