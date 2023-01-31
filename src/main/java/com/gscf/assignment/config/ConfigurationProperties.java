package com.gscf.assignment.config;

import lombok.Data;

@Data
@org.springframework.boot.context.properties.ConfigurationProperties("config")
public class ConfigurationProperties {
    private String inputFileName;
}
