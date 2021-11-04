package com.github.eugenelesnov;

import io.undertow.Undertow;

import java.lang.instrument.Instrumentation;

public class AgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        EchoProperties echoProperties = new EchoProperties(args);

        Undertow server = Undertow.builder()
                .addHttpListener(echoProperties.getPort(), echoProperties.getHost())
                .setHandler(EchoHandler.healthHandler())
                .build();

        server.start();

        System.out.println("Agent server is running..");
    }
}
