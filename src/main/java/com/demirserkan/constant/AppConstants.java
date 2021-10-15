package com.demirserkan.constant;

public class AppConstants {

    private AppConstants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String PLATEAU_LIMITS_EXCEEDED = "Plateau limits exceeded. Rover cannot move.";
    public static final String LANDING_FAILED = "Landing failed.";
}
