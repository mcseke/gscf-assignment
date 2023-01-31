package com.gscf.assignment.config;

@org.springframework.boot.context.properties.ConfigurationProperties("config")
public class ConfigurationProperties {

    private String inputFileName;

    public String getInputFileName() {
        return inputFileName;
    }
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }



}
