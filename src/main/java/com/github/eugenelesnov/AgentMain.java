package com.github.eugenelesnov;

import io.undertow.Undertow;

import java.lang.instrument.Instrumentation;

import static com.github.eugenelesnov.httphandlers.HealthHandler.healthHandler;

public class AgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        EchoProperties echoProperties = new EchoProperties(args);
        String host = echoProperties.getHost();
        Integer port = echoProperties.getPort();

        Undertow server = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(healthHandler())
                .build();

        server.start();

        System.out.println("Agent server is running..");
    }
}
