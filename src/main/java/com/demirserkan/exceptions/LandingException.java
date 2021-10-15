package com.demirserkan.exceptions;

import com.demirserkan.constant.AppConstants;

public class LandingException extends RuntimeException {
    public LandingException(String message) {
        super(AppConstants.LANDING_FAILED + " " + message);
    }
}
