package com.demirserkan.enums;

public enum Direction {

    N("W", "E"),
    E("N", "S"),
    S("E", "W"),
    W("S", "N");

    private final String left;
    private final String right;

    Direction(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }
}
