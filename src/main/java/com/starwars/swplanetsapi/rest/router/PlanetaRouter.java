package com.starwars.swplanetsapi.rest.router;


import com.starwars.swplanetsapi.rest.handler.PlanetaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class PlanetaRouter {

    private static final String PLANETAS = "/planetas";

    @Bean
    RouterFunction<ServerResponse> routes(PlanetaHandler handler) {
        return route(GET(PLANETAS), handler::buscar)
                .andRoute(GET(PLANETAS + "/{id}"), handler::buscarPorID)
                .andRoute(POST(PLANETAS), handler::criar)
                .andRoute(DELETE(PLANETAS + "/{id}"), handler::excluir)
                ;
    }

}
