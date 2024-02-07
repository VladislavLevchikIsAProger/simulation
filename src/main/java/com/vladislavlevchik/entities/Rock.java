package com.vladislavlevchik.entities;

import com.vladislavlevchik.entities.Entity;

public class Rock extends Entity {
    public Rock() {
        super("\uD83D\uDDFB");
    }

    @Override
    public String toString() {
        return getType();
    }
}
