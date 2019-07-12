package com.starwars.swplanetsapi.rest.handler;

import com.starwars.swplanetsapi.factory.PlanetaBuscaFactory;
import com.starwars.swplanetsapi.model.document.Planeta;
import com.starwars.swplanetsapi.service.PlanetaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class PlanetaHandler {

    private final PlanetaService service;
    private final PlanetaBuscaFactory buscaFactory;

    public Mono<ServerResponse> buscar(ServerRequest request) {
        return ok()
                .body(buscaFactory.buscar(request), Planeta.class);
    }

    public Mono<ServerResponse> buscarPorID(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .body(service.buscar(id), Planeta.class);
    }

    public Mono<ServerResponse> criar(ServerRequest request) {
        return request
                .bodyToMono(Planeta.class)
                .flatMap(service::criar)
                .flatMap(this::defaultWriteResponse);
    }

    public Mono<ServerResponse> excluir(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .body(service.excluir(id), Void.class);
    }

    private Mono<ServerResponse> defaultWriteResponse(Planeta planeta) {
        return created(URI.create("/planetas/" + planeta.getId()))
                .syncBody(planeta);
    }

}
