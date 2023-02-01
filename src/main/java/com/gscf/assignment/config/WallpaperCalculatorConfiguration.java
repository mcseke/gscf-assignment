package com.gscf.assignment.config;

import com.gscf.assignment.util.WallpaperCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigurationProperties.class)
public class WallpaperCalculatorConfiguration {
    @Autowired
    private ConfigurationProperties configurationProperties;

    @Bean
    public WallpaperCalculator getWallpaperCalculator() {
        WallpaperCalculator wallpaperCalculator = new WallpaperCalculator();
        System.out.println(configurationProperties.getInputFileName());
        wallpaperCalculator.setInputFilePath(configurationProperties.getInputFileName());
        wallpaperCalculator.readRoomsFromFile();
        return wallpaperCalculator;
    }
}
