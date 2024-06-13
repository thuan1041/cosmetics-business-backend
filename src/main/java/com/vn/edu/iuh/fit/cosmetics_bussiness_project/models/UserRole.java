package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

public enum UserRole {
    ADMIN(0),
    CUSTOMER(1),
    GUEST(2);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}