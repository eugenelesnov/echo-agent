package com.github.eugenelesnov;

import io.undertow.Undertow;
import io.undertow.util.Headers;

import java.lang.instrument.Instrumentation;

public class AgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        EchoProperties echoProperties = new EchoProperties();

        Undertow server = Undertow.builder()
                .addHttpListener(echoProperties.getPort(), echoProperties.getHost())
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Application is running");
                }).build();

        server.start();

        System.out.println("Agent server is running..");
    }
}
