package com.github.eugenelesnov;

import io.undertow.Undertow;

import java.lang.instrument.Instrumentation;

import static com.github.eugenelesnov.httphandlers.HealthHandler.healthHandler;

public class AgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        EchoProperties echoProperties = new EchoProperties(args);

        Undertow server = Undertow.builder()
                .addHttpListener(echoProperties.getPort(), echoProperties.getHost())
                .setHandler(healthHandler())
                .build();

        server.start();

        System.out.println("Agent server is running..");
    }
}
