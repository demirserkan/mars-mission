package com.demirserkan.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Plateau {
    private int minXLimit = 0;
    private int minYLimit = 0;
    private int maxXLimit;
    private int maxYLimit;

    public Plateau(int maxXLimit, int maxYLimit) {
        this.maxXLimit = maxXLimit;
        this.maxYLimit = maxYLimit;
    }
}
