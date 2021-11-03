package com.github.eugenelesnov;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class EchoProperties {

    private static final String PROPERTIES_FILE = "./echo.properties";
    private static final String PORT = "echo-agent.port";
    private static final String HOST = "echo-agent.host";

    private static final int DEFAULT_PORT_VALUE = 8001;
    private static final String DEFAULT_HOST_VALUE = "localhost";

    private final Properties properties = new Properties();

    public EchoProperties() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (Exception ex) {
            System.out.println("An error occurred while loading properties. Default values will be applied.");
            ex.printStackTrace();
        }
    }

    public Integer getPort() {
        return Optional.ofNullable(properties.getProperty(PORT)).map(Integer::valueOf).orElse(DEFAULT_PORT_VALUE);
    }

    public String getHost() {
        return Optional.ofNullable(properties.getProperty(HOST)).orElse(DEFAULT_HOST_VALUE);
    }
}
