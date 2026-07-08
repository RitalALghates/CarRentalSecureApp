package com.cccy225.carrental.model;

public enum ComfortLevel {
    POOR(1),
    MEDIUM(2),
    GOOD(3);

    private final int rank;

    ComfortLevel(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
