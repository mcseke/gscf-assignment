package com.gscf.assignment.components;

import com.gscf.assignment.config.ConfigurationProperties;
import com.gscf.assignment.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@EnableConfigurationProperties(ConfigurationProperties.class)
public class WallpaperCalculator {
    private static final Logger logger = LogManager.getLogger(WallpaperCalculator.class);

    private static final String DIMENSION_SEPARATOR = "x";

    private final ConfigurationProperties configurationProperties;

    @Autowired
    WallpaperCalculator(ConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    private List<Room> readRoomsFromFile() {
        List<Room> result = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource(configurationProperties.getInputFileName());
            Scanner scanner = new Scanner(resource.getFile());
            while (scanner.hasNext()) {
                String[] roomDatas = scanner.next().split(DIMENSION_SEPARATOR);
                Room room = new Room(
                        Integer.valueOf(roomDatas[0]),
                        Integer.valueOf(roomDatas[1]),
                        Integer.valueOf(roomDatas[2])
                );
                result.add(room);
            }
        } catch (IndexOutOfBoundsException | IOException e) {
            logger.error("Exception occured during reading rooms from input file", e);
            throw new RuntimeException(e);
        }
        return result;
    }


    public Long calculateWallpaperNeeded() {
        List<Room> rooms = readRoomsFromFile();

        AtomicReference<Long> wallpaperNeeded = new AtomicReference<>(0L);
        rooms.forEach((room) ->
                wallpaperNeeded.updateAndGet(v -> v + room.wallpaperNeeded())
        );
        return wallpaperNeeded.get();

    }

    public List<Room> listCubicShapedRoomsOrderedByWallpaperNeededDesc() {
        List<Room> rooms = readRoomsFromFile();
        return rooms.stream()
                .filter(room ->
                        room.getHeight().equals(room.getLength()) && room.getHeight().equals(room.getWidth())
                ).sorted((o1, o2) ->
                        Long.compare(o2.wallpaperNeeded(), o1.wallpaperNeeded())
                ).collect(Collectors.toList());
    }

    public List<Room> listRoomsAppearingMoreThenOnce() {
        List<Room> rooms = readRoomsFromFile();
        Map<Room, Long> roomsToOccuranceMapping = rooms.stream().collect(Collectors.groupingBy(room -> room, Collectors.counting()));
        return roomsToOccuranceMapping.entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
