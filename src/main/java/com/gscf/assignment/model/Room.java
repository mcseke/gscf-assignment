package com.gscf.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Room {
    private Integer length;
    private Integer width;
    private Integer height;

    private Integer calculateSurfaceArea() {
        return 2 * length * width + 2 * width * height + 2 * height * length;
    }

    public Integer wallpaperNeeded() {
        return calculateSurfaceArea() + Math.min(Math.min(length, width), height);
    }
}
