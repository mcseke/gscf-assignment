package com.gscf.assignment.util;

import com.gscf.assignment.config.ConfigurationProperties;
import com.gscf.assignment.model.Room;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@Component
@EnableConfigurationProperties(ConfigurationProperties.class)
public class WallpaperCalculator {

    @Autowired
    private ConfigurationProperties configurationProperties;

    @PostConstruct
    public void init() {
        Long wallpaperNeeded = calculateWallpaperNeeded();
        System.out.println(wallpaperNeeded);
    }

    private List<Room> readRoomsFromFile() {
        List<Room> result = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource(configurationProperties.getInputFileName());
            Scanner scanner = new Scanner(resource.getFile());
            while (scanner.hasNext()) {
                String[] roomDatas = scanner.next().split("x");
                Room room = new Room(
                        Integer.valueOf(roomDatas[0]),
                        Integer.valueOf(roomDatas[1]),
                        Integer.valueOf(roomDatas[2])
                );
                result.add(room);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public Long calculateWallpaperNeeded() {
        List<Room> rooms = readRoomsFromFile();

        AtomicReference<Long> wallpaperNeeded = new AtomicReference<>(0L);
        rooms.stream().forEach((room) -> {
            wallpaperNeeded.updateAndGet(v -> v + room.wallpaperNeeded());
        });
        return wallpaperNeeded.get();

    }


}
