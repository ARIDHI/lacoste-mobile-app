package com.lacoste.uat.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Environment {
    UAT("UAT"),
    SIT("SIT"),
    PREPROD("PREPROD");
    private final String value;
    public static Environment from(String val) {
        for (Environment p : values()) {
            if (p.value.equalsIgnoreCase(val)) return p;
        }
        throw new IllegalArgumentException("Unknown environment: " + val);
    }
}
