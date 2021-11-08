package com.github.eugenelesnov.httphandlers;

import io.undertow.server.HttpHandler;
import io.undertow.util.Headers;

import java.util.Objects;

import static io.undertow.Handlers.path;
import static io.undertow.util.Methods.GET_STRING;

public class HealthHandler {

    public static HttpHandler healthHandler() {
        return path().addExactPath("/echo/ping", exchange -> {
            if (Objects.equals(exchange.getRequestMethod().toString(), GET_STRING)) {
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send("Application is running");
            }
        });
    }
}
