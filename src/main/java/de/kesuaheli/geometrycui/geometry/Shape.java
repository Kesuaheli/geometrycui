package de.kesuaheli.geometrycui.geometry;

import net.minecraft.util.StringIdentifiable;

public enum Shape implements StringIdentifiable {
    SQUARE,
    RECTANGLE,
    CIRCLE,
    CUBOID,
    CYLINDER;

    @Override
    public String toString() {
        return switch (this) {
            case SQUARE -> "Square";
            case RECTANGLE -> "Rectangle";
            case CIRCLE -> "Circle";
            case CUBOID -> "Cuboid";
            case CYLINDER -> "Cylinder";
        };
    }

    @Override
    public String asString() {
        return this.toString().toLowerCase();
    }
}
