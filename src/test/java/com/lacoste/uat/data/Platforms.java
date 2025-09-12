package com.lacoste.uat.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Platforms {
    ANDROID("android"),
    IOS("ios");

    private final String value;
    public static Platforms from(String val) {
        for (Platforms p : values()) {
            if (p.value.equalsIgnoreCase(val)) return p;
        }
        throw new IllegalArgumentException("Unknown platform: " + val);
    }
}
