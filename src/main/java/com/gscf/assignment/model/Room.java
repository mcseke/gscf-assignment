package com.gscf.assignment.model;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return length.equals(room.length) && width.equals(room.width) && height.equals(room.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }
}
