package com.gscf.assignment;

import com.gscf.assignment.components.WallpaperCalculator;
import com.gscf.assignment.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class AssignmentApplication {

    private static final Logger logger = LogManager.getLogger(AssignmentApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AssignmentApplication.class, args);
        WallpaperCalculator wallpaperCalculator = context.getBean(WallpaperCalculator.class);

        Long wallpaperNeeded = wallpaperCalculator.calculateWallpaperNeeded();
        logger.info(String.format("Number of total square feet of wallpaper: %s", wallpaperNeeded));

        List<Room> rooms = wallpaperCalculator.listCubicShapedRoomsOrderedByWallpaperNeededDesc();
        logger.info(String.format("All rooms from input that have a cubic shape (order by total needed wallpaper descending): %s", rooms));

        List<Room> roomsAppearingMoreThenOnce = wallpaperCalculator.listRoomsAppearingMoreThenOnce();
        logger.info(String.format("Rooms from input that are appearing more than once (order is irrelevant): %s", roomsAppearingMoreThenOnce));

    }
}
