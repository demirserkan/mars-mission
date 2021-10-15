package com.demirserkan.exceptions;

import com.demirserkan.constant.AppConstants;

public class PlateauLimitsException extends RuntimeException {
    public PlateauLimitsException(String message) {
        super(AppConstants.PLATEAU_LIMITS_EXCEEDED + " " + message);
    }
}
