package com.gscf.assignment.util;

import com.gscf.assignment.model.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
public class WallpaperCalculator {
    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    private String inputFilePath;


//
//    public WallpaperCalculator(String inputFilePath) {
//        this.inputFilePath = inputFilePath;
//        List<Room> rooms = readRoomsFromFile();
//
//        AtomicReference<Long> wallpaperNeeded = new AtomicReference<>(0L);
//        rooms.stream().forEach((room) -> {
//            wallpaperNeeded.updateAndGet(v -> v + room.wallpaperNeeded());
//        });
//    }



    public List<Room> readRoomsFromFile() {
        List<Room> result = new ArrayList<>();
        try {
            System.out.println(inputFilePath);
            Resource resource = new ClassPathResource(inputFilePath);
            Scanner scanner = new Scanner(resource.getFile());
            while (scanner.hasNext()) {
                String[] roomDatas = scanner.next().split("x");
                Room room = new Room(
                        Integer.valueOf(roomDatas[0]),
                        Integer.valueOf(roomDatas[1]),
                        Integer.valueOf(roomDatas[2])
                );
                System.out.println(room);
                result.add(room);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
