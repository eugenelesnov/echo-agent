package com.github.eugenelesnov;

import io.undertow.Undertow;
import io.undertow.util.Headers;

import java.lang.instrument.Instrumentation;
import java.util.Objects;

import static io.undertow.Handlers.path;
import static io.undertow.util.Methods.GET_STRING;

public class AgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        EchoProperties echoProperties = new EchoProperties(args);

        Undertow server = Undertow.builder()
                .addHttpListener(echoProperties.getPort(), echoProperties.getHost())
                .setHandler(
                        path().addExactPath("/echo", exchange -> {
                            if (Objects.equals(exchange.getRequestMethod().toString(), GET_STRING)) {
                                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                                exchange.getResponseSender().send("Application is running");
                            }
                        })
                )
                .build();

        server.start();

        System.out.println("Agent server is running..");
    }
}
