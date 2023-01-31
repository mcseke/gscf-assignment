package com.gscf.assignment.model;

import lombok.Data;

@Data
public class Room {
    private Integer length;
    private Integer width;
    private Integer height;

    public Room(Integer length, Integer width, Integer height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    private Integer calculateSurfaceArea() {
        return 2 * length * width + 2 * width * height + 2 * height * length;
    }

    public Integer wallpaperNeeded() {
        return calculateSurfaceArea() + Math.min(Math.min(length, width), height);
    }

    @Override
    public String toString() {
        return "Room{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
