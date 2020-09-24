package com.barkovsky.serverWebcarrot.model;


import lombok.Getter;

@Getter
public enum ERole {
    ROLE_USER("user"),
    ROLE_MODERATOR("moderator"),
    ROLE_ADMIN("admin");

    private String role;

    ERole(String role) {
        this.role = role;
    }

}
