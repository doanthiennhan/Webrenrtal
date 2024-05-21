package com.example.webrented.Controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
public class SseController {

    private final Sinks.Many<String> sink;

    public SseController() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping(value = "/sse", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> streamEvents() {
        System.out.println("Sse");
        return sink.asFlux().map(data -> ServerSentEvent.builder(data).build());
    }

    @PostMapping("/reload")
    public void reload() {
        System.out.println("reload");
        sink.tryEmitNext("reload");
    }

}
