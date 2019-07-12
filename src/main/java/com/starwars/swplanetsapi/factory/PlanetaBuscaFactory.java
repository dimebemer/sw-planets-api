package com.starwars.swplanetsapi.factory;

import com.starwars.swplanetsapi.model.document.Planeta;
import com.starwars.swplanetsapi.service.PlanetaService;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
@RequiredArgsConstructor
public class PlanetaBuscaFactory {

    private final PlanetaService service;

    public Publisher<Planeta> buscar(ServerRequest request) {
        return request.queryParam("nome")
                .map(service::buscarPorNome)
                .orElseGet(service::listar);
    }
}
